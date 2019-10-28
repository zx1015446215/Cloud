package com.zhxshark.personmessage.log;

import com.alibaba.fastjson.JSON;
import com.zhxshark.personmessage.model.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuxin
 * @date 2019/10/12 11:38
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    MongoTemplate mongoTemplate;

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:hh";

    private long startTime;

    private long endTime;

    private Log log;

    @Pointcut("execution(* com.zhxshark.personmessage.controller.*.*(..))")
    public void cut(){}

    /**
     * 在方法前执行
     * @param joinPoint
     */
    @Before("cut()")
    public void doBefore(JoinPoint joinPoint){
        logger.warn("doBefore");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        log = new Log();
        startTime = System.currentTimeMillis();  //开始时间
        String format = sdf.format(new Date(startTime));
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.setStartDate(format);   //开始时间
        log.setUrl(request.getRequestURI());  //url
        log.setMethod(request.getMethod());  //method
        log.setIp(request.getRemoteAddr());  //IP地址
        log.setClass_method(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());  //方法名
        log.setArgs(joinPoint.getArgs());  //参数

//        logger.warn(JSON.toJSONString(log));
    }

    /**
     * 在方法返回和抛出异常时执行
     * @param joinPoint
     */
    @After("cut()")
    public void doAfter(JoinPoint joinPoint){
        logger.warn("after");
    }

    /**
     * 在返回值的时候
     * @param joinPoint
     * @param obj
     */
    @AfterReturning(pointcut = "cut()",returning = "obj")
    public void doAfterReturning(JoinPoint joinPoint, Object obj){
        logger.warn("afterReturning");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        endTime = System.currentTimeMillis();
        log.setEndDate(sdf.format(new Date(endTime)));  //结束时间
        log.setDate(endTime-startTime);  //耗时
        log.setResponse(JSON.toJSONString(obj));  //返回内容
        mongoTemplate.save(log);
        logger.warn(JSON.toJSONString(log));
    }

    /**
     * 已经抛出异常
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut ="cut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e){
        logger.warn("afterThrowing");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        endTime = System.currentTimeMillis();
        log.setEndDate(sdf.format(new Date(endTime)));  //结束时间
        log.setDate(endTime-startTime);  //耗时
        log.setE(e);  //异常
        mongoTemplate.save(log);
        logger.warn(JSON.toJSONString(log));
    }
}
