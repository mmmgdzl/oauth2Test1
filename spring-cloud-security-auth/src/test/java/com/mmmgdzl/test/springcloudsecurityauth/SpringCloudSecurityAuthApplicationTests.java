package com.mmmgdzl.test.springcloudsecurityauth;

import com.mmmgdzl.test.springcloudsecurityauth.mapper.OauthClientDetailsMapper;
import com.mmmgdzl.test.springcloudsecurityauth.mapper.TbPermissionMapper;
import com.mmmgdzl.test.springcloudsecurityauth.mapper.TbUserMapper;
import com.mmmgdzl.test.springcloudsecurityauth.pojo.OauthClientDetails;
import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudSecurityAuthApplicationTests {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void contextLoads() {
        System.out.println(oauthClientDetailsMapper.selectByPrimaryKey("SYSTEM"));
        System.out.println(tbPermissionMapper.getByUserName("admin"));
        TbUser admin =
                tbUserMapper.selectByUserName("admin");
        System.out.println(admin.toString());
    }

}
