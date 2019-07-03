package com.mmmgdzl.test.springcloudsecurityauth.config;

import com.mmmgdzl.test.springcloudsecurityauth.extra.SecurityPasswordEncoder;
import com.mmmgdzl.test.springcloudsecurityauth.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 设置默认的加密方式
        return new SecurityPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                // 在内存中创建用户并为密码加密
//                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN");
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionFixation().none();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    //配置内存模式的用户
    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        System.out.println(passwordEncoder().encode("123456"));
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("123456")).authorities("ADMIN").build());
        manager.createUser(User.withUsername("user").password(passwordEncoder().encode("123456")).authorities("USER").build());
        return manager;
    }

    /**
     * 需要配置这个支持password模式
     * support password grant type
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
