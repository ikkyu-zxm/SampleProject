package cn.ikkyu.sample.test.webservice;

import cn.ikkyu.sample.test.util.CodeGeneratorUtils;
import com.alibaba.fastjson.JSON;
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


}
