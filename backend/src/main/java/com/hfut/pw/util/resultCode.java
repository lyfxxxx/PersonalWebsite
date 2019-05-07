package com.hfut.pw.util;

public enum resultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);
    public Integer code;
    resultCode(Integer code) {
        this.code = code;
    }
}
