package com.zhxshark.personmessage.mapper;

import com.zhxshark.personmessage.model.ZxRoles;
import com.zhxshark.personmessage.model.ZxUserRoles;

import java.util.List;

public interface ZxUserRolesMapper {
    int deleteByPrimaryKey(String fdId);

    int insert(ZxUserRoles record);

    int insertSelective(ZxUserRoles record);

    ZxUserRoles selectByPrimaryKey(String fdId);

    int updateByPrimaryKeySelective(ZxUserRoles record);

    int updateByPrimaryKey(ZxUserRoles record);

    List<ZxRoles> selectByFdCode(String fdCode);
}