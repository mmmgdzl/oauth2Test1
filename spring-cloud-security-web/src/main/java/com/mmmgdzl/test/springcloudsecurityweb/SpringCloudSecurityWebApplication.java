package com.mmmgdzl.test.springcloudsecurityweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SpringCloudSecurityWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSecurityWebApplication.class, args);
    }

}
