package com.mmmgdzl.test.springcloudsecurityzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableOAuth2Sso
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SpringCloudSecurityZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSecurityZuulApplication.class, args);
    }

}
