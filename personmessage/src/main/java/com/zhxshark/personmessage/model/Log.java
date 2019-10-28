package com.zhxshark.personmessage.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author zhuxin
 * @date 2019/10/12 13:57
 */
public class Log implements Serializable {
    private String startDate;    //请求开始时间
    private String endDate;   //结束时间
    private Long date;  //过程时间
    private String ip;   //请求的ip
    private String principal;  //用户的凭证
    private String url;  //请求的url
    private String method;   //请求的方法(get/post)
    private String class_method;  //请求的类方法
    private Object[] args;   //请求的参数
    private String response;  //返回的内容
    private Exception e;


    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClass_method() {
        return class_method;
    }

    public void setClass_method(String class_method) {
        this.class_method = class_method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

