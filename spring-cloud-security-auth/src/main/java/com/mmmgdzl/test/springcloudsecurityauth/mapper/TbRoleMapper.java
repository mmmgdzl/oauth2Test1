package com.mmmgdzl.test.springcloudsecurityauth.mapper;

import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    TbRole selectByPrimaryKey(Long id);

    List<TbRole> selectByUserId(@Param("UserId") Long userId);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKey(TbRole record);
}