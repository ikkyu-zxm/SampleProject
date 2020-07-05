package xyz.ikkyu.sample.test.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xinming
 * @Date 2020/5/10 21:58
 */
@Slf4j
public class ExecutorTest {


    @Test
    public void factoryNameTest() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),
                new IThreadFactory(),
                (r, executor) -> log.error("thread runtime exception"));
        log.info("创建线程池成功");
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.submit(() -> {
                String name = Thread.currentThread().getName();
                log.info("thread name is {}" , name);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(100000);
        threadPoolExecutor.shutdown();
    }


    class IThreadFactory implements ThreadFactory {

        private AtomicInteger threadNum = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(nextThreadName());
            return thread;
        }

        private String nextThreadName() {
            return "test_poll_" + threadNum.incrementAndGet();
        }
    }

}
