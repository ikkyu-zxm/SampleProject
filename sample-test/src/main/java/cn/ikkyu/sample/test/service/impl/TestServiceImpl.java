package cn.ikkyu.sample.test.service.impl;

import cn.ikkyu.sample.test.dao.po.Cat;
import cn.ikkyu.sample.test.dao.repository.CatRepository;
import cn.ikkyu.sample.test.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xinming
 * @Date 2019/12/23 22:25
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private CatRepository catRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testTransactional(Cat cat) {
        catRepository.save(cat);
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

        int i = 1 / 0;

        testTransactional(cat2);
    }
}
