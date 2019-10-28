package com.zhxshark.personmessage.mapper;

import com.zhxshark.personmessage.model.ZxUser;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@CacheNamespaceRef(ZxUserMapper.class)
public interface ZxUserMapper {
    int deleteByPrimaryKey(String fdId);

    int insert(ZxUser record);

    int insertSelective(ZxUser record);

    ZxUser selectByPrimaryKey(String fdId);

    /**
     * 按条件更新用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ZxUser record);

    int updateByPrimaryKey(ZxUser record);

    /**
     * 根据登录名查询用户信息
     * @param fdLoginName
     * @return
     */
    ZxUser selectByLoginName(String fdLoginName);

    /**
     * 按条件查询所有用户并分页
     * @param zxUser
     * @return
     */
    List<ZxUser> selectAllUserByPage(ZxUser zxUser);
}