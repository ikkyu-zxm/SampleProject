package cn.ikkyu.sample.test.lock;

import base.xyz.ikkyu.lock.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * @author xinming
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void redisDistributeTest() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10));

        CountDownLatch countDownLatch = new CountDownLatch(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(() -> {
                try (RedisDistributedLock lock = new RedisDistributedLock("key", "value", 10, redisTemplate)) {
                    cyclicBarrier.await();
                    boolean successes = lock.getLock();
                    log.info("我是线程 {}  抢锁状态 {}",Thread.currentThread().getName(),successes);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPoolExecutor.shutdown();
    }

}
