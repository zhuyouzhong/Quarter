package com.example.quarter.bean;

import java.util.List;

/**
 * Created by 祝文 on 2017/11/29.
 */

public class SendBean {

     private String msg;
     private String code;

    public SendBean() {
    }

    public SendBean(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String toString() {
        return "SendBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
