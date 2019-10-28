package com.zhxshark.personmessage.commen.result;

/**
 * @author zhuxin
 * @date 2019/9/23 15:25
 */

import java.io.Serializable;

/**
 * 用于封装AJAX调用以后的JSON返回值
 * 其中正确返回值:
 *  {state:0, data:返回数据, message:错误消息}
 * 错误返回值:
 *  {state:1, data:null, message:错误消息}
 */
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -3644950655568598241L;

    public static final int SUCCESS = 200;
    public static final int ERROR = 999;

    /**
     * 返回是否成功的状态, 0表示成功,
     * 1或其他值 表示失败
     */
    private int state;
    /**
     * 成功时候,返回的JSON数据
     */
    private Object data;
    /**
     * 是错误时候的错误消息
     */
    private String message;

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getERROR() {
        return ERROR;
    }

    public JsonResult() {
    }


    public JsonResult(int state, Object data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public JsonResult(Object data){
        state = SUCCESS;
        this.data=data;
        message="";
    }


    public int getState() {
        return state;
    }


    public void setState(int state) {
        this.state = state;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + "]";
    }

}
