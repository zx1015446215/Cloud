package com.zhxshark.personmessage.mapper;

import com.zhxshark.personmessage.model.ZxRoles;

public interface ZxRolesMapper {
    int deleteByPrimaryKey(String fdId);

    int insert(ZxRoles record);

    int insertSelective(ZxRoles record);

    ZxRoles selectByPrimaryKey(String fdId);

    int updateByPrimaryKeySelective(ZxRoles record);

    int updateByPrimaryKey(ZxRoles record);
}