package com.krahsxhz.zuul.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理GateWay转发时候的错误，
 * @author zhuxin
 * @date 2019/10/15 10:24
 */
@Controller
public class GatewayErrorController implements ErrorController {

    @Value("${ExceptionResult.status}")
    private int status ;
    @Value("${ExceptionResult.message}")
    private String message;
    @Value("${ExceptionResult.error}")
    private String error;
    @Value("${ExceptionResult.path}")
    private String path;

    private static final Logger logger = LoggerFactory.getLogger(GatewayErrorController.class);

    @RequestMapping
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView){
        logger.warn("进入errorController");
//        int statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//        String message = String.valueOf(request.getAttribute("javax.servlet.error.message"));
//        String requestURI = request.getRequestURI();
//        return new ExceptionResult(status,error,message,path);
            modelAndView.setViewName("/error/error");
            return  modelAndView;
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
