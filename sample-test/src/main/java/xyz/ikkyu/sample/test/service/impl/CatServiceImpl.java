package xyz.ikkyu.sample.test.service.impl;

import xyz.ikkyu.sample.test.dao.po.Cat;
import xyz.ikkyu.sample.test.dao.repository.CatRepository;
import xyz.ikkyu.sample.test.domain.req.CatReqVO;
import xyz.ikkyu.sample.test.domain.resp.CatRespVO;
import xyz.ikkyu.sample.test.service.CatService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    @Override
    public Boolean saveCat(CatReqVO reqVO) {

        int id = new Random().nextInt(10000);
        Cat cat = new Cat();
        BeanUtils.copyProperties(reqVO, cat);
        cat.setUserId(id);
        catRepository.save(cat);
        return Boolean.TRUE;
    }

    @Override
    public List<CatRespVO> searchByName(String name) {
        List<Cat> byUserNameLike = catRepository.findByUserNameLike("%" + name + "%");
        return Lists.transform(byUserNameLike, po -> {
            CatRespVO vo = new CatRespVO();
            BeanUtils.copyProperties(po, vo);
            return vo;
        });
    }

}
