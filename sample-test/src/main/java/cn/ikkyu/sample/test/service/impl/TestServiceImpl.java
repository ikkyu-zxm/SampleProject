package cn.ikkyu.sample.test.service.impl;

import cn.ikkyu.sample.test.service.TestService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xinming
 * @Date 2019/12/23 22:25
 */
public class TestServiceImpl implements TestService {



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testTransactional() {

    }
}
