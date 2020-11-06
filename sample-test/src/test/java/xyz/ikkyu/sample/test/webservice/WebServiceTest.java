package xyz.ikkyu.sample.test.webservice;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.Test;
import xyz.ikkyu.sample.test.domain.GoodsQualificationType;
import xyz.ikkyu.sample.test.webservice.test.DTSTORE;
import xyz.ikkyu.sample.test.webservice.test.DTSTORES;
import xyz.ikkyu.sample.test.webservice.test.SISTORETYPESOut;
import xyz.ikkyu.sample.test.webservice.test.SISTORETYPESOutService;

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




    @Test
    public void invokeQueryStoreTypeJaxWsServiceTest() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(SISTORETYPESOut.class);
        jaxWsProxyFactoryBean.setWsdlLocation("wsdl/dev/queryStoreType.wsdl");
//        jaxWsProxyFactoryBean.setAddress("http://sap-pi-qas.1919.cn:50000/dir/wsdl?p=ic/eb84248abe6537c7b2e51d3de3d74de0");
        jaxWsProxyFactoryBean.setServiceName( SISTORETYPESOutService.SERVICE);
        jaxWsProxyFactoryBean.setEndpointName(SISTORETYPESOutService.HTTPPort);
        SISTORETYPESOut synGoods = jaxWsProxyFactoryBean.create(SISTORETYPESOut.class);


        AuthorizationPolicy authorizationPolicy =
                new AuthorizationPolicy();
        authorizationPolicy.setUserName("SRM_PI");
        authorizationPolicy.setPassword("a12345678");
        authorizationPolicy.setAuthorizationType("Basic");

        HTTPClientPolicy httpPolicy = new HTTPClientPolicy();
        httpPolicy.setConnectionTimeout(30 * 1000);
        httpPolicy.setAllowChunking(false);
        httpPolicy.setReceiveTimeout(2 * 60 * 1000);
        HTTPConduit httpConduit = null;
        try {
            httpConduit = (HTTPConduit) ClientProxy.getClient(synGoods).getConduit();
            httpConduit.setClient(httpPolicy);
            httpConduit.setAuthorization(authorizationPolicy);
            DTSTORES dtstores = new DTSTORES();

            DTSTORE dtstore = new DTSTORE();
            dtstore.setStoreCode("W031");
            dtstores.setStoreCodes(Lists.newArrayList(dtstore));
            DTSTORES dtstores1 = synGoods.siSTORETYPESOut(dtstores);
            Gson gson = new Gson();
            System.out.println("=====================>==============>===============");
            System.out.println(gson.toJson(dtstores1));
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



}
