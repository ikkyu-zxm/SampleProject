package xyz.ikkyu.sample.test.webservice;

import xyz.ikkyu.sample.test.domain.GoodsQualificationType;
import com.alibaba.fastjson.JSON;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.Test;

import javax.xml.namespace.QName;

/**
 * @author xinming
 * @Date 2019/12/28 15:49
 */
public class WebServiceTest {

    @Test
    public void invokeWebServiceTest() {
        MobileCodeWS mobileCodeWS = new MobileCodeWS();
        MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getMobileCodeWSSoap();
        String mobileCodeInfo = mobileCodeWSSoap.getMobileCodeInfo("18181529097", "");
        System.out.println(mobileCodeInfo);
    }


    @Test
    public void invokeJaxWsServiceTest() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(MobileCodeWSHttpGet.class);
        jaxWsProxyFactoryBean.setWsdlLocation("wsdl/dev/MobileCodeWS.wsdl");
        jaxWsProxyFactoryBean.setServiceName( new QName("http://WebXml.com.cn/", "MobileCodeWSHttpGet"));
        jaxWsProxyFactoryBean.setEndpointName(new QName("http://WebXml.com.cn/","MobileCodeWSHttpGet"));
        MobileCodeWSHttpGet synGoods = jaxWsProxyFactoryBean.create(MobileCodeWSHttpGet.class);

        HTTPClientPolicy httpPolicy = new HTTPClientPolicy();
        HTTPConduit httpConduit = null;
        try {
            httpConduit = (HTTPConduit) ClientProxy.getClient(synGoods).getConduit();
            httpConduit.setClient(httpPolicy);
            String mobileCodeInfo = synGoods.getMobileCodeInfo("18181529097", "");
            System.out.println(mobileCodeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpConduit != null) {
                // 关闭流
                httpConduit.close();
            }
        }

//        WebServiceUtils.getWebServiceByUrl(, "")

    }


    @Test
    public void webServiceUtilTest() {
        GoodsQualificationType[] values = GoodsQualificationType.values();
        System.out.println(JSON.toJSONString(values));
        GoodsQualificationType value = values[1];
        System.out.println(value.getDesc());

    }

}
