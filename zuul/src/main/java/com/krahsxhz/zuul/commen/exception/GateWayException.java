package com.krahsxhz.zuul.commen.exception;

import com.krahsxhz.zuul.commen.result.ExceptionResult;
import com.netflix.zuul.exception.ZuulException;

/**
 *
 * 自定义网关异常
 * @author zhuxin
 * @date 2019/10/15 10:41
 */
public class GateWayException extends ZuulException {

    public GateWayException(ExceptionResult exceptionResult){
        super(exceptionResult.getMessage(), exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public GateWayException(int code, String message){
        super(message,code,message);
    }
}
