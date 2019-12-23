package cn.ikkyu.ftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author xinming
 * @Date 2019/11/23 12:26
 */
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients()
@SpringBootApplication
public class FtpServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(FtpServiceApplication.class,args);
    }
}
