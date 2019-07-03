package com.mmmgdzl.test.springcloudsecurityauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.mmmgdzl.test.springcloudsecurityauth.mapper")
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SpringCloudSecurityAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSecurityAuthApplication.class, args);
    }

}
