package com.mmmgdzl.test.springcloudsecurityweb.controller;

import com.mmmgdzl.test.springcloudsecurityweb.feign.TestFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class TestController {

    @Autowired
    private TestFeignClient testFeignClient;

    @RequestMapping("/admin/goName")
    @ResponseBody
    public String goName() {
        return testFeignClient.getName();
    }

    @RequestMapping("/see/goSee")
    @ResponseBody
    public String goSee() {
        return testFeignClient.getSee();
    }

    @GetMapping("/api/member")
    @ResponseBody
    public Principal user(Principal member) {
        //获取当前用户信息
        return member;
    }

    @RequestMapping("/")
    public String goIndex() {
        return "index";
    }

    @RequestMapping("/next")
    public String goNext() {
        return "next";
    }

    @GetMapping("/gogo")
    @ResponseBody
    public String user() {
        //获取当前用户信息
        return "gogo";
    }

}
