package com.mmmgdzl.test.springcloudsecurityauth.service;

import com.mmmgdzl.test.springcloudsecurityauth.mapper.TbPermissionMapper;
import com.mmmgdzl.test.springcloudsecurityauth.mapper.TbRoleMapper;
import com.mmmgdzl.test.springcloudsecurityauth.mapper.TbUserMapper;
import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbPermission;
import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbRole;
import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbUser;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbRoleMapper tbRoleMapper;
    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //获取用户
        TbUser tbUser = tbUserMapper.selectByUserName(username);
        if(tbUser == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (TbRole role : tbUser.getRoles()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getEnname());
            grantedAuthorities.add(grantedAuthority);

            //循环获取继承的角色作为权限加入
            TbRole tmpRole = role;
            while(!tmpRole.getParentId().equals(0L)) {
                tmpRole = tbRoleMapper.selectByPrimaryKey(tmpRole.getParentId());
                grantedAuthority = new SimpleGrantedAuthority(tmpRole.getEnname());
                grantedAuthorities.add(grantedAuthority);

            }
//            //获取权限(仅使用到role层级)
//            for (TbPermission permission : role.getPermissions()) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getEnname());
//                grantedAuthorities.add(authority);
//            }
        }
        return new User(tbUser.getUsername(), tbUser.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}
