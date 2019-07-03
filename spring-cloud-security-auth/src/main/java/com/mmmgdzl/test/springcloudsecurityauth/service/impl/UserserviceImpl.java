package com.mmmgdzl.test.springcloudsecurityauth.service.impl;

import com.mmmgdzl.test.springcloudsecurityauth.mapper.TbUserMapper;
import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbUser;
import com.mmmgdzl.test.springcloudsecurityauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public int insertUser() {
        TbUser tbUser = new TbUser();
        tbUser.setId(0L);
        tbUser.setUsername("test");
        tbUser.setPassword("987987");
        tbUser.setEmail("456");
        tbUser.setPassword("123");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        userMapper.insert(tbUser);
        tbUser.setUsername("test2");
        userMapper.insert(tbUser);

        return 0;
    }
}
