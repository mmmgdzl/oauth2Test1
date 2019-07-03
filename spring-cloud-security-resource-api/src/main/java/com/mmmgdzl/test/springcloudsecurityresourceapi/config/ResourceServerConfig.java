package com.mmmgdzl.test.springcloudsecurityresourceapi.config;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * 〈OAuth资源服务配置〉
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //关闭跨域访问拦截器
                .csrf().disable()
                //异常拦截器
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                //设置请求拦截匹配器
                .requestMatchers().antMatchers("/**")
                .and()
                //设置权限请求匹配器
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/see/**").hasAuthority("SEE")
                .and()
                .httpBasic();
    }

    /**
     * 从配置文件中读取授权服务信息
     */
    @Bean
    @ConditionalOnProperty("security.oauth2.client.client-id")
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
                                                            OAuth2ProtectedResourceDetails resource) {
        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, resource);
    }
}
