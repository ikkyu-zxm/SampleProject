package xyz.ikkyu.sample.test.webservice;

import xyz.ikkyu.sample.test.domain.GoodsRegisterQualificationRespVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * @author xinming
 * @Date 2019/12/29 9:31
 */

@WebService()
public interface HelloWebService {

    @WebMethod(operationName = "hi")
    @WebResult(name="ret")
    String hello(List<GoodsRegisterQualificationRespVO> respVOList);

}
