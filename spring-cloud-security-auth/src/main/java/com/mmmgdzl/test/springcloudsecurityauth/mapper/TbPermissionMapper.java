package com.mmmgdzl.test.springcloudsecurityauth.mapper;

import com.mmmgdzl.test.springcloudsecurityauth.pojo.TbPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbPermission record);

    int insertSelective(TbPermission record);

    TbPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbPermission record);

    int updateByPrimaryKey(TbPermission record);

    List<TbPermission> getByUserName(@Param("userName") String userName);

    List<TbPermission> selectByRoleId(@Param("RoleId") Long roleId);
}