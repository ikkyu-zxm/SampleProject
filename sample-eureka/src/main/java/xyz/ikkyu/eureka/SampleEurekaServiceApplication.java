package xyz.ikkyu.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author xinming
 * @Date 2019/11/22 22:20
 */
@EnableEurekaServer
@SpringBootApplication
public class SampleEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleEurekaServiceApplication.class, args);
    }
}
