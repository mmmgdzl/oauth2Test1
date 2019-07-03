package com.mmmgdzl.test.springcloudsecurityzuul.config;

import com.mmmgdzl.test.springcloudsecurityzuul.filter.ShowSessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {



    @Bean
    public FilterRegistrationBean sessionHelpFilter() {
        return new FilterRegistrationBean(new ShowSessionFilter());
    }



}
