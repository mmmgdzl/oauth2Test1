package com.mmmgdzl.test.springcloudsecurityauth.mapper;

import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbUser;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);
    TbUser selectByUserName(@Param("userName") String userName);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
}