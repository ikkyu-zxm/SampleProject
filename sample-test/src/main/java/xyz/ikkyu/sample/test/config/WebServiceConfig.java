package xyz.ikkyu.sample.test.config;

import xyz.ikkyu.sample.test.webservice.HelloWebService;
import xyz.ikkyu.sample.test.webservice.HelloWebServiceImpl;
import lombok.Data;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.xml.ws.Endpoint;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * @author xinming
 * @Date 2019/12/29 14:36
 */
@Data
@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;

    @Bean("cxfServletRegistration")
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Bean
    @Order(HIGHEST_PRECEDENCE)
    public HelloWebService getHelloWebService() {
        return new HelloWebServiceImpl();
    }

    @Bean
    @Autowired
    public Endpoint endpoint(HelloWebService helloWebService) {
        EndpointImpl endpoint = new EndpointImpl(bus, helloWebService);
        endpoint.publish("/hello");
        return endpoint;
    }

//
//    @Bean
//    @Order(HIGHEST_PRECEDENCE)
//    public DemandWS getDemandService() {
//        return new DemandWSImpl();
//    }
//
//    @Bean
//    @Autowired
//    public Endpoint endpointDemand(DemandWS demandWS) {
//        EndpointImpl endpoint = new EndpointImpl(bus, demandWS);
//        endpoint.publish("/demandTest");
//        return endpoint;
//    }


}
