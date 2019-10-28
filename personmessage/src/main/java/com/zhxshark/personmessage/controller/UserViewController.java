package com.zhxshark.personmessage.controller;

import com.zhxshark.personmessage.service.GithubAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuxin
 * @date 2019/10/14 13:44
 */
@Controller
@RequestMapping("/view/user")
public class UserViewController {

    private final static Logger logger = LoggerFactory.getLogger(UserViewController.class);

    @Autowired
    GithubAuthService githubAuthService;

    /**
     * github页面登录
     */
    @RequestMapping("/github")
//    @ResponseBody
    public String githubLogin(HttpServletResponse response) throws IOException {
        logger.warn("进入github");
        String authorizationUrl = githubAuthService.getAuthorizationUrl();
        response.sendRedirect(authorizationUrl);
        return  authorizationUrl;
    }

    /**
     * qq页面登录
     */
    @RequestMapping("/qq")
    public void qqLogin(){

    }
}
