package com.mmmgdzl.test.springcloudsecurityzuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    @ResponseBody
    public Integer test(HttpSession session) {
        System.out.println("zuul2:" + session.getId());
        System.out.println("nameIs:" + session.getAttribute("myNameIs"));
        return 0;
    }

    @RequestMapping("/store")
    @ResponseBody
    public String store(String accessToken, Integer expireTime, HttpSession session) {
        //存储的应比实际的小以容传输时间差(这里减100s)
        expireTime -= 100;
        if(expireTime < 1) {
            expireTime = 1;
        }
        //存储session
//        stringRedisTemplate.opsForValue().set("ACCESS_TOKEN-SESSION:" + session.getId(), accessToken, expireTime, TimeUnit.SECONDS);

        session.setAttribute("accessToken", accessToken);

        return session.getId();
    }

}
