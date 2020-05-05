package cn.ikkyu.sample.test.webservice;

import base.xyz.ikkyu.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
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
    public void testJedis() {

        Set<String> hkeys = jedisPool.getResource().hkeys("terminal-order");
        System.out.println(JsonUtils.obj2Json(hkeys));
    }

    @Test
    public void testRedisTemplate() {
        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        byte[] bytes = keySerializer.serialize("terminal-order");
        Set keys = redisTemplate.opsForHash().keys(bytes);
        System.out.println(JsonUtils.obj2Json(keys));
    }
}
