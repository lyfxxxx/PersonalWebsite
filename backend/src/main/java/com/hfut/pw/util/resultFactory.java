package com.hfut.pw.util;

public class resultFactory {
    public static result buildSuccessRes(Object data) {
        return buildRes(resultCode.SUCCESS, "success", data);
    }

    public static result buildFailedRes(String message) {
        return buildRes(resultCode.FAIL, message, null);
    }

    public static result buildRes(resultCode code, String message, Object data) {
        return new result(code.code, message, data);
    }

     public static result buildRes(Integer code, String message, Object data) {
        return new result(code, message, data);
    }
}
