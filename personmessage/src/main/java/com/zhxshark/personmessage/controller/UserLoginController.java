package com.zhxshark.personmessage.controller;


/**
 * 账号管理域：包含账号的模型和提供的基本接口能力
 *
 * 登录模块域： 涉及登录和免登流程
 *
 * 注册账号域： 账号注册和账号创建能力
 *
 * 个人中心域： 涉及个人中心管理
 */

import com.alibaba.fastjson.JSON;
import com.zhxshark.personmessage.commen.result.JsonResult;
import com.zhxshark.personmessage.model.ZxUser;
import com.zhxshark.personmessage.service.GithubAuthService;
import com.zhxshark.personmessage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 登录模块
 * @author zhuxin
 * @date 2019/9/24 15:50
 */

@RequestMapping("/user/login")
@RestController
public class UserLoginController {

    private final static Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    GithubAuthService githubAuthService;
    /**
     * 一般单点登录
     * @return
     */
    @RequestMapping("/normal")
    public JsonResult normalLogin(@RequestBody ZxUser zxUser){
        logger.warn("=============普通用户登录验证=============");
        return userService.userNomalLogin(zxUser);
    }

    /**
     * github回调地址
     */
    @RequestMapping("/github/callback")
    public JsonResult githubCallback(HttpServletRequest request){
        logger.warn("==============进入github/callback===========");
//        Enumeration<String> parameterNames = request.getParameterNames();
//        for (Enumeration e = parameterNames;e.hasMoreElements();){
//            String thisName = e.nextElement().toString();
//            String thisValue = request.getParameter(thisName);
//            logger.info(thisName+"  :  "+thisValue);
//        }
        JsonResult result = githubAuthService.getAccessToken(request.getParameter("code"));
        if(result.getState()==1){  //如果没有获取到access_token
            return result;
        }
        String accessToken = String.valueOf(result.getData());
        JsonResult userInfo = githubAuthService.getUserInfo(accessToken);
        return userInfo;
    }

}
