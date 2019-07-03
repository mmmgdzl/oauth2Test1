package com.mmmgdzl.test.springcloudsecurityzuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * 〈Security配置〉
 * Security配置
 * @author wangmx
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@Order(99)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public OAuth2RestOperations restOperations(
//            OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
//        return new OAuth2RestTemplate(resource, context);
//    }
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置请求头X-FRAME-OPTIONS相同源域名调用
        http.csrf().disable().headers().frameOptions().sameOrigin();
//        http.sessionManagement()
//                .sessionFixation().none();
    }
}
