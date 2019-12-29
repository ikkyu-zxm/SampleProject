package cn.ikkyu.sample.test.webservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author xinming
 * @Date 2019/12/29 14:45
 */
@Slf4j
@Component
@WebService(serviceName = "HelloWebService",
        targetNamespace = "http://webservice.test.sample.ikkyu.cn/",
        endpointInterface = "cn.ikkyu.sample.test.webservice.HelloWebService")
public class HelloWebServiceImpl implements HelloWebService {

    @Override
//    @WebMethod(operationName = "hi")
//    @WebResult(name="ret")
    public String hello(@WebParam(name = "str") String str) {
        log.info("invoke method hello--------{}", str);
        return "hello webService";
    }
}
