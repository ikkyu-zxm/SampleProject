package cn.ikkyu.sample.test.lock;

import base.xyz.ikkyu.lock.RedisDistributedLock;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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



    @Test
    public void export() throws MalformedURLException {
        Path outputFile = Paths.get("doc/clearing");
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        Swagger2MarkupConverter converter = Swagger2MarkupConverter
                .from(new URL("http://192.168.72.177:17005/v2/api-docs?group=WINE-ADVISER#"))
                .withConfig(config)
                .build();

        converter.toFolder(outputFile);
    }
}
