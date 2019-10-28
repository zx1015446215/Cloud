package com.zhxshark.personmessage.commen.result;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuxin
 * @date 2019/10/14 19:42
 */
public class ExceptionResult implements Serializable {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 常用的错误码
     */
    //服务器正常响应
    private static final int STATUS_SUCCESS = 200;
    //该资源在上次请求之后没有任何修改
    private static final int STATUS_NO_CHANGE = 304;
    //无法找到请求的资源
    private static final int STATUS_NOT_FOUND_RESOURCE = 400;
    //访问资源的权限不够
    private static final int STATUS_AUTHORITY = 401;
    //需要访问的资源不存在
    private static final int STATUS_NOT_FOUND = 404;
    //需要访问的资源被禁止
    private static final int STATUS__FORBIDDEN = 405;
    //访问的资源需要代理身份验证
    private static final int STATUS_NEED_AUTHENTICATE = 407;
    //请求的URL太长
    private static final int STATUS_URL_TOOLONG = 414;
    //服务器内部错误
    private static final int STATUS_SERVER_ERROR = 500;

    /**
     * 日期
     */
    private String date;
    /**
     * 状态
     */
    private int status;
    /**
     * 错误内容
     */
    private String error;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 错误路径
     */
    private String path;

    public ExceptionResult() {
    }

    public ExceptionResult(int status, String error, String message, String path) {
        date = format.format(new Date());
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ExceptionResult(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
