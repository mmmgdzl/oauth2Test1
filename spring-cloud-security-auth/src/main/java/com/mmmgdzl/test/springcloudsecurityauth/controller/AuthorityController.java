package com.mmmgdzl.test.springcloudsecurityauth.controller;

import com.mmmgdzl.test.springcloudsecurityauth.mapper.TbUserMapper;
import com.mmmgdzl.test.springcloudsecurityauth.service.UserService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * 〈会员Controller〉
 */
@RestController
@RequestMapping("/api")
public class AuthorityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/member")
    public Principal user(Principal member) {
        //获取当前用户信息
        return member;
    }

//    @DeleteMapping(value = "/exit")
//    public Result revokeToken(String access_token) {
//        //注销当前用户
//        Result result = new Result();
//        if (consumerTokenServices.revokeToken(access_token)) {
//            result.setCode(ResultCode.SUCCESS.getCode());
//            result.setMsg("注销成功");
//        } else {
//            result.setCode(ResultCode.FAILED.getCode());
//            result.setMsg("注销失败");
//        }
//        return result;
//    }



    @GetMapping("/learn/test1")
    private Integer getGO(HttpSession session) {
        System.out.println("auth:" + session.getId());
        session.setAttribute("myNameIs", "mmmgdzl");
        return 0;
    }


}
