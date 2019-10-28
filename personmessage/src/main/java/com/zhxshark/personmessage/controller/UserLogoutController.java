package com.zhxshark.personmessage.controller;

import com.zhxshark.personmessage.commen.result.JsonResult;
import com.zhxshark.personmessage.model.ZxUser;
import com.zhxshark.personmessage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuxin
 * @date 2019/9/29 10:59
 */
@RestController
@RequestMapping("/user/logout")
public class UserLogoutController {

    private Logger logger = LoggerFactory.getLogger(UserLogoutController.class);

    @Autowired
    UserService userService;

    /**
     * 用户注销
     * @return
     */
    @RequestMapping()
    public JsonResult logout(@RequestBody ZxUser zxUser){
        return userService.userNomalLogout(zxUser);
    }

    @RequestMapping("/reuse")
    public JsonResult loginReuse(@RequestBody ZxUser zxUser){
        return  userService.userNomalReUse(zxUser);
    }
}
