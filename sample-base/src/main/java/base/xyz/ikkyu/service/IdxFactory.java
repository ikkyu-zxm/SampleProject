package base.xyz.ikkyu.service;

/**
 * @author: defei
 * @date 2017/9/11
 */
public interface IdxFactory {

	long MAX_WORKER_ID = 1 << 9;
	long MAX_DATA_CENTER_ID = 1;

	/**
	 * 返回一个数据中心id
	 * @return
	 */
	default long getDataCenterIdx(){
		return 0;
	}

	/**
	 * 返回一个workerId
	 * @return
	 */
	long getWorkerIdx();


}
