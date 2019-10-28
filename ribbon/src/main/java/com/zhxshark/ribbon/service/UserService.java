package com.zhxshark.ribbon.service;


import com.alibaba.fastjson.JSON;
import com.zhxshark.model.ZxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhuxin
 * @date 2019/9/24 17:00
 */
@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    public String nomalLogin(ZxUser zxUser){
        
        return restTemplate.postForEntity("http://PERSON-MESSAGE/user/login/normal", (JSON)JSON.toJSON(zxUser), String.class).getBody();

    }
}
