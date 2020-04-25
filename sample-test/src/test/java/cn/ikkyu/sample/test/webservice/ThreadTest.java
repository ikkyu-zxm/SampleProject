package cn.ikkyu.sample.test.webservice;

import cn.ikkyu.sample.test.util.CodeGeneratorUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author xinming
 * @Date 2020/3/7 15:54
 */
public class ThreadTest {


    @Test
    public void fixedThreadTest() throws ExecutionException, InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        long starTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Future<Integer> submit = threadPoolExecutor.submit(() -> methodTest(finalI));
            list.add(submit.get());
        }
//        System.out.println(list);
        System.out.println(System.currentTimeMillis() - starTime);

    }


    private Integer methodTest(int i) {
        String name = Thread.currentThread().getName();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread name " + name + "time :" + System.currentTimeMillis());
        if (i % 100 == 0) {
            return 1;
        }
        return 0;
    }


    @Test
    public void forTest() {
        long starTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Integer integer = methodTest(i);
            list.add(integer);
        }
        System.out.println(list);
        System.out.println(System.currentTimeMillis() - starTime);
    }


    @Test
    public void codeGeneratorTest() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        Set<String> codeSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Future<String> submit = threadPoolExecutor.submit(CodeGeneratorUtils::getNewAutoNum);
            String s = submit.get();
            codeSet.add(s);
        }
        System.out.println(JSON.toJSONString(codeSet));
    }




    @Test
    public void notifyTest() throws InterruptedException {

        NotifyTest notifyTest = new NotifyTest();
        Thread thread1 = new Thread(notifyTest::run, "线程一");
        Thread thread2 = new Thread(notifyTest::transferNotify, "线程二");
        Thread thread3 = new Thread(notifyTest::run, "线程三");
        thread1.start();
        thread3.start();
        Thread.sleep(5000);
        thread2.start();



        /*System.out.println("我是主线程，我要 notify 了");
        synchronized (cat) {
            notify();
        }*/

    }

    class NotifyTest {

        private Integer  a = 12;

        public void transferNotify() {
            Thread thread = Thread.currentThread();
            System.out.println("我是 :" + thread.getName() + " notify");
            synchronized (this) {
                notify();
            }
        }


        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println("我是 :" + thread.getName() + " 我开始执行方法了");
            try {
                System.out.println(thread.getName() + " 调用wait");
               synchronized (this) {
                   wait();
               }
            } catch (Exception e) {
                System.out.println("噢哦，异常了" + thread.getName());
                System.out.println(e);
            }
            System.out.println("我 ：" + thread.getName() + "又活过来了");

        }
    }





    @Test
    public void guvaCacheTest() {

        Cache<String, Integer> cache = CacheBuilder.newBuilder().build();

        cache.put("key", 0);


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(0));


        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);


        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(() -> {
                try {
                    countDownLatch.countDown();
                    cyclicBarrier.await();
                    Integer key = cache.getIfPresent("key");

                } catch (Exception e) {
                    System.out.println("子线程出错了");
                }
            });
        }


    }




}
