package com.mmmgdzl.test.springcloudsecurityresourceapi.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TestService {

    @RequestMapping("/admin/getName")
    @ResponseBody
    String getName();

    @RequestMapping("/see/getSee")
    @ResponseBody
    String getSee();

}
