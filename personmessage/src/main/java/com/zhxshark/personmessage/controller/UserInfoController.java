package com.zhxshark.personmessage.controller;

import com.zhxshark.personmessage.commen.result.JsonResult;
import com.zhxshark.personmessage.model.ZxUser;
import com.zhxshark.personmessage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuxin
 * @date 2019/9/29 16:17
 */
@RestController
@RequestMapping("/user/info")
@Api(value = "UserInfoController(用来返回用户的一些基本信息)")
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(UserLogoutController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/findUsers",method = RequestMethod.POST)
    @ApiOperation(value = "根据条件去查询用户信息", notes = "默认分页，若需自己配置，则传输pageSize和pageNo")
//    @ApiImplicitParam(paramType = "query",name = "findUser",value = "用户信息",required = false,dataTypeClass = JsonResult.class)
    public JsonResult findUser(@RequestBody(required = false) ZxUser zxUser){
        return userService.findUsers(zxUser);
    }

}
