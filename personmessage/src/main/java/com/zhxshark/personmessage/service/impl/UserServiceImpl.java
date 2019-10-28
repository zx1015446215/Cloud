package com.zhxshark.personmessage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhxshark.personmessage.commen.result.JsonResult;
import com.zhxshark.personmessage.commen.util.GenerateUtile;
import com.zhxshark.personmessage.mapper.ZxUserMapper;
import com.zhxshark.personmessage.model.ZxUser;
import com.zhxshark.personmessage.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * @author zhuxin
 * @date 2019/9/24 16:06
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    ZxUserMapper zxUserMapper;
    /**
     * @param zxUser
     * @return
     */
    @Override
    @Transactional
    public JsonResult userNomalLogin(ZxUser zxUser) {
        ZxUser user = null;
        JSONObject obj = new JSONObject();
        if(StringUtils.isEmpty(zxUser.getFdLoginName())){
           return null;
        }
        user = zxUserMapper.selectByLoginName(zxUser.getFdLoginName());
        if (user == null){
            return new JsonResult(1,null,"Account does not exist");
        }
        //判断密码是否正确
        if(!user.getFdPass().equals(zxUser.getFdPass())){
            return new JsonResult(1,null,"wrong password");
        }
        //判断用户账号是否有效
        if(user.getFdIsavailable().equals("0")){
            return new JsonResult(1,null,"The account is invalid, please contact the administrator");
        }
        obj.put("fdRoles", user.getRoles());
        return new JsonResult(obj);
    }

    @Override
    @Transactional
    public JsonResult userNomalRegister(ZxUser zxUser) {
        if(zxUser==null){
            return new JsonResult(1,null,"Registration failed, please contact the administrator");
        }
        if(zxUserMapper.selectByLoginName(zxUser.getFdLoginName())!=null){
            return new JsonResult(1,null,"username already exists");
        }
        //信息注册
        zxUser.setFdId(GenerateUtile.getUUID()); //自动生成ID
        zxUser.setFdIsavailable("1");
        int insert = zxUserMapper.insert(zxUser);
        if(insert>0){
            return new JsonResult(zxUser);
        }else{
            return new JsonResult(1,null,"Registration failed, please contact the administrator");
        }
    }

    @Override
    public JsonResult userNomalLogout(ZxUser zxUser) {
        JsonResult jsonResult = new JsonResult();
        if(zxUser.getFdId()==null){
            return new JsonResult(1,null,"Account logout failed, please contact the administrator");
        }
        zxUser.setFdIsavailable("0");
        int flag = zxUserMapper.updateByPrimaryKeySelective(zxUser);
        if(flag>0){
            return new JsonResult(zxUser);
        }else{
            return new JsonResult(1,null,"Account logout failed, please contact the administrator");
        }
    }

    @Override
    public JsonResult userNomalReUse(ZxUser zxUser) {
        if(zxUser.getFdId()==null){
            return new JsonResult(1,null,"account_recovery_failed, please contact the administrator");
        }
        zxUser.setFdIsavailable("1");
        int flag = zxUserMapper.updateByPrimaryKeySelective(zxUser);
        if(flag>0){
            return new JsonResult(zxUser);
        }else{
            return new JsonResult(1,null,"account_recovery_failed, please contact the administrator");
        }
    }

    @Override
    public JsonResult findUsers(ZxUser zxUser) {
        List<ZxUser> zxUsers = zxUserMapper.selectAllUserByPage(zxUser);
        return new JsonResult(zxUsers);
    }
}
