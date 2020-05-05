package cn.ikkyu.sample.test.webservice;

import base.xyz.ikkyu.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xinming
 * @Date 2020/5/5 14:10
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private JedisPool jedisPool;

    @Test
    public void dleExpiredKey() {
//        ScanOptions.ScanOptionsBuilder scanOptionsBuilder = ScanOptions.scanOptions();
//        scanOptionsBuilder.match("2019*");
//        scanOptionsBuilder.count(1000);
//
//        ScanOptions scanOptions = scanOptionsBuilder.build();
//        Cursor<Map.Entry<String, String>> scan = redisTemplate.opsForHash().scan("terminal-order", scanOptions);

        ScanParams match = new ScanParams().match("2019*");
        ScanResult<Map.Entry<String, String>> hscan = jedisPool.getResource().hscan("terminal-order", "0", match);

        List<Map.Entry<String, String>> result = hscan.getResult();

        Set<String> keySet = new HashSet<>();
        for (Map.Entry<String, String> entry : result) {
            keySet.add(entry.getKey());
        }
        System.out.println("查询到key ：" + JsonUtils.obj2Json(keySet));
//        Set<String> keySet = new HashSet<>();
//
//        while (scan.hasNext()) {
//            Map.Entry<String, String> entry = scan.next();
//            keySet.add(entry.getKey());
//        }
//        System.out.println("查询到key ：" + JsonUtils.obj2Json(keySet));;

    }

    @Test
    public void testJedisHkeys() throws Exception {
        Set<String> hkeys = jedisPool.getResource().hkeys("terminal-order");

//        System.out.println("查询到所有key ：" + JsonUtils.obj2Json(hkeys));
        Set<String> fileds = new HashSet<>();
        for (String hkey : hkeys) {
            if (hkey.startsWith("2019-05")) {
                fileds.add(hkey);
            }
        }

//        System.out.println("查询到 2019 05 key  " + JsonUtils.obj2Json(fileds));

        int num = fileds.size() % 500 == 0 ? fileds.size() / 500 : fileds.size() / 500 + 1;

        num = Math.min(num, 7);

        for (int i = 0; i < num; i++) {
            System.out.println("i = " + i);
            jedisPool.getResource().hdel("terminal-order", fileds.stream().skip(i * 500).limit(500).toArray(String[]::new));
            Thread.sleep(1000);
        }

        log.info("删除完成，共删除 {} 个key", fileds.size());
    }

    @Test
    public void testJedis() {

        Boolean exists = jedisPool.getResource().exists("terminal-order");
//        Map<String, String> stringStringMap = jedisPool.getResource().hgetAll("terminal-order");

        ScanParams scanParams = new ScanParams();
        scanParams.match("2019-05-06*");
        scanParams.count(1000);
        ScanResult<Map.Entry<String, String>> hscan = jedisPool.getResource().hscan("terminal-order", ScanParams.SCAN_POINTER_START, scanParams);

        Set<String> keySet = new HashSet<>();

        for (int i = 0; i < 6; i++) {
            String cursor = hscan.getCursor();
            System.out.println("cursor = " + cursor);
            for (Map.Entry<String, String> entry : hscan.getResult()) {
                keySet.add(entry.getKey());
            }
            if (cursor.equals("0")) {
                System.out.println("scan 完成");
                break;
            }
            hscan = jedisPool.getResource().hscan("terminal-order", cursor, scanParams);
        }
        System.out.println("key size :" + keySet.size());
        System.out.println("查询到 key : " + JsonUtils.obj2Json(keySet));
        jedisPool.getResource().hdel("terminal-order", keySet.toArray(new String[0]));
//        while (true) {
//            String cursor = hscan.getCursor();
//            System.out.println("cursor = " + cursor);
//            for (Map.Entry<String, String> entry : hscan.getResult()) {
//                keySet.add(entry.getKey());
//            }
//            if (cursor.equals("0")) {
//                break;
//            }
//            hscan = jedisPool.getResource().hscan("terminal-order", cursor, scanParams);
//        }
//
//        System.out.println("查询到 key : " + JsonUtils.obj2Json(keySet));
    }

    @Test
    public void testRedisTemplate() {
        ScanOptions.ScanOptionsBuilder scanOptionsBuilder = new ScanOptions.ScanOptionsBuilder();
        scanOptionsBuilder.match("2019-06*");
        scanOptionsBuilder.count(100);
        ScanOptions build = scanOptionsBuilder.build();
        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan("terminal-order", build);

        Set<String> keySet = new HashSet<>();
        while (scan.hasNext()) {
            keySet.add(scan.next().getKey().toString());
        }
        System.out.println("查询到key ： " + JsonUtils.obj2Json(keySet));
    }
}
