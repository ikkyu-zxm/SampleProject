package cn.ikkyu.ftp;

import cn.ikkyu.ftp.annotations.MethodParameterResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @Author xinming
 * @Date 2019/11/23 12:26
 */
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients()
@SpringBootApplication
public class FtpServiceApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args)  {
        SpringApplication.run(FtpServiceApplication.class, args);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(new MethodParameterResolver());
    }
}
