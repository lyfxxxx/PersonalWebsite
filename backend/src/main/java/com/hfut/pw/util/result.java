package com.hfut.pw.util;

public class result {
    //响应状态码
    private Integer code;
    private String message;
    private Object data;
    result (int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
