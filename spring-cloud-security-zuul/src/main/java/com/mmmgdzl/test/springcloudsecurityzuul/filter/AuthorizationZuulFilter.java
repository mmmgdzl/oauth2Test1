package com.mmmgdzl.test.springcloudsecurityzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Component
public class AuthorizationZuulFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 指定该Filter的类型
     * ERROR_TYPE = "error";
     * POST_TYPE = "post";
     * PRE_TYPE = "pre";
     * ROUTE_TYPE = "route";
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 指定该Filter执行的顺序（Filter从小到大执行）
     * DEBUG_FILTER_ORDER = 1;
     * FORM_BODY_WRAPPER_FILTER_ORDER = -1;
     * PRE_DECORATION_FILTER_ORDER = 5;
     * RIBBON_ROUTING_FILTER_ORDER = 10;
     * SEND_ERROR_FILTER_ORDER = 0;
     * SEND_FORWARD_FILTER_ORDER = 500;
     * SEND_RESPONSE_FILTER_ORDER = 1000;
     * SIMPLE_HOST_ROUTING_FILTER_ORDER = 100;
     * SERVLET_30_WRAPPER_FILTER_ORDER = -2;
     * SERVLET_DETECTION_FILTER_ORDER = -3;
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 指定需要执行该Filter的规则
     * 返回true则执行run()
     * 返回false则不执行run()
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUrl = request.getRequestURL().toString();

        // 请求URL内不包含login或join则需要经过该过滤器，即执行run()
//        return !requestUrl.contains("login") && !requestUrl.contains("join");

        //这个后面要判断是否是需要授权的页面
        //只验证非静态资源
        return !requestUrl.contains("/static/");
    }

    /**
     * 该Filter具体的执行活动
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpSession session = request.getSession();
//
//        // 若session中不包含userId，则这次请求视为未登录请求，不给予路由，而提示“请登录”
//        if (httpSession.getAttribute("userId") == null) {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(200);
//            // 为使得中文字符不乱码
//            ctx.getResponse().setCharacterEncoding("UTF-8");
//            ctx.setResponseBody("请登录");
//        }

        //获取accessToken
//        String token = stringRedisTemplate.opsForValue().get("ACCESS_TOKEN-SESSION:" + session.getId());
        String token = (String) session.getAttribute("accessToken");
        if(token != null) {
            //Header Authorization 值:Bearer cde6f2ba-03b9-47c1-b2de-1778c29dbbd7
            //添加请求头
            System.out.println(request.getRequestURL());
            ctx.addZuulRequestHeader("Authorization", "Bearer " + token);
        }

        return null;
    }

}
