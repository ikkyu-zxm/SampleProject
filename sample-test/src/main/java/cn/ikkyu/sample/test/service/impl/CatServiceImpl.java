package cn.ikkyu.sample.test.service.impl;

import cn.ikkyu.sample.test.dao.repository.CatRepository;
import cn.ikkyu.sample.test.service.CatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xinming
 * @Date 2019/12/28 9:52
 */
@Service
public class CatServiceImpl implements CatService {


    @Resource
    private CatRepository catRepository;


    @Override
    public Long getCatValuation() {
        Map<String, Object> objectMap = catRepository.getCount();
        Object rows = objectMap.get("rows");
        System.out.println(rows);
        return Long.parseLong(rows.toString()) ;
    }

}
