package com.example.l_backend.util;

public enum ResultEnum {
    USER_IS_EXISTS(2, "用户已存在");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
