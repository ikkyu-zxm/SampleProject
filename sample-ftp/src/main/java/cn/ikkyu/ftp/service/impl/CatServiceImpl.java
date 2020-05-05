package cn.ikkyu.ftp.service.impl;

import cn.ikkyu.ftp.annotations.DataParameterConvert;
import cn.ikkyu.ftp.domain.ProductQualificationReqVo;
import cn.ikkyu.ftp.service.CatService;
import org.springframework.stereotype.Service;

/**
 * @author xinming
 */
@Service
public class CatServiceImpl implements CatService {


    @Override
    @DataParameterConvert
    public void testConvert(ProductQualificationReqVo data) {
        System.out.println(data);
    }
}
