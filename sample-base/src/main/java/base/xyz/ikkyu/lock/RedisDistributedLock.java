package base.xyz.ikkyu.lock;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author xinming
 */
@Slf4j
public class RedisDistributedLock implements AutoCloseable {

    private RedisTemplate redisTemplate;

    private String key;

    private String value;

    private long expireDate = 20;


    public RedisDistributedLock(String key , String value , long expireDate,RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.key = key;
        this.value = value;
        this.expireDate = expireDate;
    }



    public boolean getLock() {
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value, expireDate, TimeUnit.SECONDS);
        log.info("thread name {} lock state {}",Thread.currentThread().getName(),aBoolean);
        return aBoolean;
//        return true;
    }


    public boolean unLock() {
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                "   return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "   return 0\n" +
                "end";

        RedisScript<Boolean> redisScript = RedisScript.of(script,Boolean.class);
        ArrayList<String> keys = Lists.newArrayList(key);
        Boolean result = (Boolean) redisTemplate.execute(redisScript, keys, value);
        log.info("我是 {}  释放锁的结果 {}",Thread.currentThread().getName(),result);
        return result;
    }





    @Override
    public void close() throws Exception {
//        DefaultRedisScript redisScript = new DefaultRedisScript();
//        redisScript.setResultType(Boolean.class);
//        redisScript.setScriptSource();
//        redisTemplate.execute()
        unLock();
    }
}
