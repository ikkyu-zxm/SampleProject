package cn.ikkyu.sample.test.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author xinming
 * @Date 2019/12/29 9:31
 */

@WebService()
public interface HelloWebService {

    @WebMethod(operationName = "hi")
    @WebResult(name="ret")
    String hello(@WebParam(name = "str") String str);

}
