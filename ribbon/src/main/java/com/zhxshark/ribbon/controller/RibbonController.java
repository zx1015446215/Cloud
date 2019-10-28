package com.zhxshark.ribbon.controller;

import com.alibaba.fastjson.JSON;
import com.zhxshark.model.ZxUser;
import com.zhxshark.ribbon.service.HelloService;
import com.zhxshark.ribbon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 最好使用feign进行均衡负载，feign即包含ribbon，同时编写的代码更加的简洁明了
 */
@RestController
public class RibbonController {

    private Logger logger = LoggerFactory.getLogger(RibbonController.class);

    @Autowired
    private HelloService helloService;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }
    @RequestMapping(value = "/user/**/*")
    public String normalLogin(@RequestBody(required = false) ZxUser zxUser, HttpServletRequest
                              request){
        //组成新的URL
        StringBuilder serviceId = new StringBuilder("http://PERSON-MESSAGE");
        serviceId.append(request.getRequestURI());
        logger.warn("转发地址:"+serviceId);
        return restTemplate.postForEntity(serviceId.toString(), zxUser,String.class).getBody();
    }

    @RequestMapping(value = "/user/info/**/*")
    public String userInfo(@RequestBody(required = false) ZxUser zxUser, HttpServletRequest
            request){
        //组成新的URL
        StringBuilder serviceId = new StringBuilder("http://PERSON-MESSAGE");
        serviceId.append(request.getRequestURI());
        logger.warn("转发地址:"+serviceId);
        return restTemplate.postForEntity(serviceId.toString(),zxUser, String.class).getBody();
    }

}
