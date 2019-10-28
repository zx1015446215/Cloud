package com.zhxshark.personmessage.service;

import com.zhxshark.personmessage.commen.result.JsonResult;
import com.zhxshark.personmessage.model.ZxUser;

/**
 * @author zhuxin
 * @date 2019/9/24 16:06
 */
public interface UserService {
    /**
     * 用户登录
     * @param zxUser
     * @return
     */
    JsonResult userNomalLogin(ZxUser zxUser);

    /**
     * 用户注册
     * @param zxUser
     * @return
     */
    JsonResult userNomalRegister(ZxUser zxUser);

    /**
     * 用户注销，假删除
     * @param zxUser
     * @return
     */
    JsonResult userNomalLogout(ZxUser zxUser);

    /**
     * 恢复用户使用
     * @param zxUser
     * @return
     */
    JsonResult userNomalReUse(ZxUser zxUser);

    /**
     * 根据条件查询用户，分页操作使用filter
     * @param zxUser
     * @return
     */
    JsonResult findUsers(ZxUser zxUser);
}
