package cn.ikkyu.sample.test.service.impl;

import cn.ikkyu.sample.test.dao.po.Cat;
import cn.ikkyu.sample.test.dao.repository.CatRepository;
import cn.ikkyu.sample.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.ikkyu.component.service.base.service.TransactionOptDelayer;

import javax.annotation.Resource;

/**
 * @author xinming
 * @Date 2019/12/23 22:25
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private CatRepository catRepository;

//    @Autowired
//    private RedisTemplate redisTemplate;

    private TransactionOptDelayer delayer = new TransactionOptDelayer();

    private static final String OK = "OK";


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testTransactional(Cat cat) {
        catRepository.save(cat);
        delayer.executeAfterTransactionCommit(() -> {
            log.info("事务执行完成");
        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testTransaction() {

        Cat cat1 = new Cat();
        cat1.setUserId(1);
        cat1.setAge(1);
        cat1.setSex("m");
        cat1.setUserName("cat1");
        testTransactional(cat1);

        Cat cat2 = new Cat();
        cat2.setUserId(2);
        cat2.setAge(2);
        cat2.setSex("m");
        cat2.setUserName("cat1");


        testTransactional(cat2);
    }

    @Override
    public String saveString(String name, String value) {

//        redisTemplate.opsForValue().set(name, value);
        return OK;
    }

    @Override
    public String saveHash(String hkey ,String key ,String value) {
//        redisTemplate.opsForHash().put(hkey,key,value);
        return OK;
    }

    @Override
    public String saveSet(String key ,Object[] values) {
//        redisTemplate.opsForSet().add(key, values);
        return OK;
    }

    @Override
    public String saveSortedSet(String key, String value, double sort) {
//        redisTemplate.opsForZSet().add(key, value, sort);
        return OK;
    }


    @Override
    public String incrementSortedSet(String key, String value, double sort) {
//        redisTemplate.opsForZSet().incrementScore(key, value, sort);
        return OK;
    }


    @Override
    public String incrementString(String name) {
//        redisTemplate.opsForValue().increment(name, 1);
        return OK;
    }

    @Override
    public String incrementHash(String hkey ,String key) {
//        redisTemplate.opsForHash().increment(hkey,key,1);
        return OK;
    }

}
