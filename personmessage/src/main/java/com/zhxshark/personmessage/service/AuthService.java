package com.zhxshark.personmessage.service;

import com.zhxshark.personmessage.commen.result.JsonResult;

/**
 * @author zhuxin
 * @date 2019/10/12 16:01
 */
public interface AuthService {

    /**
     * 根据code获取Token
     * @param code
     * @return
     */
    JsonResult getAccessToken(String code);

    /**
     * 根据Token获取openId
     * @param accessToken
     * @return
     */
    JsonResult getOpenId(String accessToken);

    /**
     * 根据code刷新Token
     * @param code
     * @return
     */
    JsonResult refreshToken(String code);

    /**
     * 拼接授权Url
     * @return
     */
    String getAuthorizationUrl();

    /**
     * 根据Token和OpenId获取用户信息
     * @param accessToken
     * @return
     */
    JsonResult getUserInfo(String accessToken);
}
