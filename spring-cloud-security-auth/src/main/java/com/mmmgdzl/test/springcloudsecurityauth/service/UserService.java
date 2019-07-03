package com.mmmgdzl.test.springcloudsecurityauth.service;

import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional
    int insertUser();

}
