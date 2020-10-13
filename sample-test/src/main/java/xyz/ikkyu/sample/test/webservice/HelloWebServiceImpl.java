package xyz.ikkyu.sample.test.webservice;

import xyz.ikkyu.sample.test.domain.GoodsRegisterQualificationRespVO;
import lombok.extern.slf4j.Slf4j;

import javax.jws.WebService;
import java.util.List;

/**
 * @author xinming
 * @Date 2019/12/29 14:45
 */
@Slf4j
@WebService(serviceName = "HelloWebService",
        targetNamespace = "http://webservice.test.sample.ikkyu.cn/",
        endpointInterface = "xyz.ikkyu.sample.test.webservice.HelloWebService")
public class HelloWebServiceImpl implements HelloWebService {

    @Override
//    @WebMethod(operationName = "hi")
//    @WebResult(name="ret")
    public String hello(List<GoodsRegisterQualificationRespVO> respVOList) {
        log.info("-------------hello");
        log.info("invoke method hello--------{}", respVOList);
        return "hello webService";
    }
}
