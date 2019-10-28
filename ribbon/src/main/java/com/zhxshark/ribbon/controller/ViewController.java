package com.zhxshark.ribbon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuxin
 * @date 2019/10/14 10:45
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    private final static Logger logger = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/user/**")
    @ResponseBody
    public String userlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //组成新的URL
        StringBuilder serviceId = new StringBuilder("http://PERSON-MESSAGE");
        serviceId.append(request.getRequestURI());
        logger.warn("转发地址:"+serviceId);
        String body = restTemplate.getForEntity(serviceId.toString(), String.class).getBody();
        logger.warn("返回内容:"+body);
//        response.sendRedirect(body);
        return body;
    }

}
