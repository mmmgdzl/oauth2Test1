package com.mmmgdzl.test.springcloudsecurityzuul.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ShowSessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("zuul:" + ((HttpServletRequest)request).getSession().getId());
        chain.doFilter(request, response);
    }
}
