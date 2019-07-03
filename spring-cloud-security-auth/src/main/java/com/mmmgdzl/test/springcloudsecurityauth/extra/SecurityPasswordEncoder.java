package com.mmmgdzl.test.springcloudsecurityauth.extra;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class SecurityPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        //将密码序列转换为MD5加密的序列
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //执行编码
        String encodedRawPassword = encode(rawPassword);
        //返回对照结果
        return encodedPassword.equals(encodedRawPassword);
    }
}
