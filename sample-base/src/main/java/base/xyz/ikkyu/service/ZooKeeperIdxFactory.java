package base.xyz.ikkyu.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 根据需要手动配置
 * @author: defei
 * @date 2017/9/11
 */

public class ZooKeeperIdxFactory implements IdxFactory {

	private CuratorFramework curator;

	private static final String soaIdxBaseNodePath = "/com/biz/cmp/soaIdx";

	private static final String NODE_SEPARATOR = "/";

	private Integer workerId = null;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ZooKeeperIdxFactory(CuratorFramework curator) {
		Assert.notNull(curator,"必须提供一个已启动的curator客户端");
		this.curator = curator;
	}

	@PostConstruct
	void initWorkerId(){
		try{
			if (curator.checkExists().forPath(soaIdxBaseNodePath) == null) {
				curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(soaIdxBaseNodePath);
			}
			// 当前存活的节点
			List<String> aliveNodes = curator.getZookeeperClient().getZooKeeper().getChildren(soaIdxBaseNodePath, false);
			if (!CollectionUtils.isEmpty(aliveNodes)) {
				for (int i = 1; i <= MAX_WORKER_ID; i++) {
					if (!aliveNodes.contains(String.valueOf(i))) {
						workerId = i;
						break;
					}
				}
				if(workerId==null){
					throw new IllegalStateException("没有可分配的workerId");
				}
			} else {
				workerId = 1;
			}
			// 创建临时节点
			curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
					.forPath(soaIdxBaseNodePath + NODE_SEPARATOR + workerId);

		}	 catch (IllegalStateException e){
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			workerId = workerId == -1 ? 1 : workerId;
		}
		logger.info("ZooKeeperIdxFactory inited, worderId:{}", workerId);
	}

	@Override
	public long getWorkerIdx() {
		if (workerId != null) {
            return workerId;
        }
		initWorkerId();
		return workerId;
	}
}
