package base.xyz.ikkyu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.ikkyu.common.service.IIdService;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author: defei
 * @date 2017/9/11
 */

@Service
public class IdService implements IIdService {

	private final static long twepoch = 1409030641843L;
	// 机器标识位数
	private final static long workerIdBits = 9L;
	// 数据中心标识位数
	private final static long dataCenterIdBits = 1L;
	// 机器ID最大值
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	// 数据中心ID最大值
//	private final static long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
	// 毫秒内自增位
	private final static long sequenceBits = 12L;
	// 机器ID偏左移12位
	private final static long workerIdShift = sequenceBits;
	// 数据中心ID左移17位
	private final static long dataCenterIdShift = sequenceBits + workerIdBits;
	// 时间毫秒左移22位
	private final static long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

	private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

	private static long lastTimestamp = -1L;

	private long sequence = 0L;

	private long workerId = -1;

	private long dataCenterId = -1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private IdxFactory idxFactory;

	public IdService(IdxFactory idxFactory) {
		this.idxFactory = idxFactory;
	}

	@PostConstruct
	public void init(){
		workerId = idxFactory.getWorkerIdx();
		dataCenterId = idxFactory.getDataCenterIdx();
	}

	public synchronized long getNextId() {
		if(workerId==-1)throw new IllegalStateException("id service 没有初始化完成");
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			try {
				throw new Exception(
						"Clock moved backwards.  Refusing to generate id for " + (lastTimestamp
								- timestamp) + " milliseconds");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (lastTimestamp == timestamp) {
			// 当前毫秒内，则+1
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				// 当前毫秒内计数满了，则等待下一秒
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		// ID偏移组合生成最终的ID，并返回ID
		long nextId =
				((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (
						workerId << workerIdShift) | sequence;

		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}


    @Override
    public Long nextLongId() {
        return getNextId();
    }

    @Override
    public String nextStringId() {
        return String.valueOf(getNextId());
    }
	
	/**
	 * 获取UUID，没有'-'
	 * @return id
	 */
	@Override
	public String nextUUID() {
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
