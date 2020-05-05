package cn.ikkyu.ftp.api;

import cn.ikkyu.ftp.annotations.ParameterData;
import cn.ikkyu.ftp.domain.ProductQualificationReqVo;
import cn.ikkyu.ftp.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinming
 */
@RestController()
@RequestMapping("sample/ftp/cat")
public class CatController {

    @Autowired
    private CatService catService;

//    @ParameterData
    @PostMapping("/test")
    public Object test(@ParameterData ProductQualificationReqVo vo) {
        return vo;
//        ProductQualificationReqVo data = vo.getData();
//        catService.testConvert(new ProductQualificationReqVo());
    }
}
