package com.zhxshark.personmessage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxshark.personmessage.model.ZxUser;
import com.zhxshark.personmessage.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonmessageApplicationTests {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    UserService userService;

    @Before // 这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
    }


    @Test
    public void testController(){
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login/github"))
                    .andExpect(MockMvcResultMatchers.status().is(200)).andDo(MockMvcResultHandlers.print()).andReturn();
            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);
            JSONObject resultObj = JSON.parseObject(result);
            System.out.println(resultObj.toJSONString());
            // 判断接口返回json中success字段是否为true
//            Assert.assertTrue(resultObj.getBooleanValue("success"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void contextLoads() {
        //查询
        ZxUser select = new ZxUser();
        ZxUser insert = new ZxUser();
        insert.setFdLoginName("zzSzz");
        insert.setFdPass("zzz");
        System.out.println("=============第一次查询================");
        userService.findUsers(select);
        System.out.println("=============第二次查询================");
        userService.findUsers(select);
        System.out.println("===========插入用户==============");
        userService.userNomalRegister(insert);
        System.out.println("==============第三次查询===============");
        userService.findUsers(select);
    }

}
