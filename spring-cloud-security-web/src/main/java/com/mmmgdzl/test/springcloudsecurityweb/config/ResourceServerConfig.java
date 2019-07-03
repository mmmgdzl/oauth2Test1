package com.mmmgdzl.test.springcloudsecurityweb.config;

import com.mmmgdzl.test.springcloudsecurityweb.extra.XKOauth2FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

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
//                //异常拦截器
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                //设置权限请求匹配器
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/**").permitAll()
                ;
//                .antMatchers("/static/**").permitAll()
//                .antMatchers("/druid/**").permitAll()
//                .antMatchers("/layim/**").permitAll()
//                .antMatchers("/actuator/**").permitAll()
//                .antMatchers("/xk/protect/**").hasAuthority("PROTECT")
//                .antMatchers("/xk/admin/**").hasAuthority("ADMIN")
//                .antMatchers("/xk/super/**").hasAuthority("SUPER")
                ;
//        http.sessionManagement()
//                .sessionFixation().none();
    }

    /**
     * 从配置文件中读取授权服务信息
     */
    @Bean
    @ConditionalOnProperty("security.oauth2.client.client-id")
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
                                                            OAuth2ProtectedResourceDetails resource) {
        //使用token拦截型
        return new XKOauth2FeignRequestInterceptor(oAuth2ClientContext, resource);
    }


}
