package cn.ikkyu.sample.test.config;

import cn.ikkyu.sample.test.webservice.HelloWebService;
import lombok.Data;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

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
    @Autowired
    public Endpoint endpoint(HelloWebService helloWebService) {
        EndpointImpl endpoint = new EndpointImpl(bus, helloWebService);
        endpoint.publish("/hello");
        return endpoint;
    }

}
