package com.mmmgdzl.test.springcloudsecurityresourceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringCloudSecurityResourceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSecurityResourceApiApplication.class, args);
    }

}
