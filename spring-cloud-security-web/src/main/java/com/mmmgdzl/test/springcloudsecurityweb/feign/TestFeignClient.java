package com.mmmgdzl.test.springcloudsecurityweb.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("XK-RESOURCE-API")
public interface TestFeignClient {

    @RequestMapping("/admin/getName")
    String getName();

    @RequestMapping("/see/getSee")
    String getSee();

}
