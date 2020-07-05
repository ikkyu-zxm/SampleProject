package xyz.ikkyu.sample.test.api;

import xyz.ikkyu.sample.test.util.WebServiceUtils;
import xyz.ikkyu.sample.test.webservice.MobileCodeWSSoap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinming
 * @Date 2019/12/29 16:42
 */
@RestController
@RequestMapping("api/webservice")
public class WebServiceController {

    @GetMapping("invoke")
    public void invokeWebservice() {
//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//        jaxWsProxyFactoryBean.setServiceClass(MobileCodeWSSoap.class);
//        jaxWsProxyFactoryBean.setWsdlLocation("wsdl/dev/MobileCodeWS.wsdl");
//        jaxWsProxyFactoryBean.setServiceName( new QName("http://WebXml.com.cn/", "MobileCodeWSHttpGet"));
//        jaxWsProxyFactoryBean.setEndpointName(new QName("http://WebXml.com.cn/","MobileCodeWSHttpGet"));
////        jaxWsProxyFactoryBean.setAddress("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
//        MobileCodeWSHttpGet synGoods = jaxWsProxyFactoryBean.create(MobileCodeWSHttpGet.class);
//
//        HTTPClientPolicy httpPolicy = new HTTPClientPolicy();
//        HTTPConduit httpConduit = null;
//        try {
//            httpConduit = (HTTPConduit) ClientProxy.getClient(synGoods).getConduit();
//            httpPolicy.setConnectionTimeout(30 * 1000);
//            httpPolicy.setReceiveTimeout(30 * 1000);
//
//            httpConduit.setClient(httpPolicy);
//            String mobileCodeInfo = synGoods.getMobileCodeInfo("18181529097", "");
//            System.out.println(mobileCodeInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (httpConduit != null) {
//                // 关闭流
//                httpConduit.close();
//            }
//        }
        MobileCodeWSSoap webServiceByUrl = WebServiceUtils.getWebServiceByUrl(MobileCodeWSSoap.class, "");
        String mobileCodeInfo = webServiceByUrl.getMobileCodeInfo("18181529097", "");
        System.out.println(mobileCodeInfo);

    }

}
