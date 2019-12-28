package cn.ikkyu.sample.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author xinming
 * @Date 2019/11/23 16:03
 */
@EnableFeignClients
@EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories("cn.ikkyu.sample.test.dao")
public class TestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestServiceApplication.class, args);
    }
}
