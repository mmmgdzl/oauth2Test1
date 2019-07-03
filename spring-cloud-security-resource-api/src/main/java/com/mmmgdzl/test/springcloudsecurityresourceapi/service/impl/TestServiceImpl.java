package com.mmmgdzl.test.springcloudsecurityresourceapi.service.impl;

import com.mmmgdzl.test.springcloudsecurityresourceapi.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String getName() {
        return "MMMGDZL";
    }

    @Override
    public String getSee() {
        return "让我康康";
    }
}
