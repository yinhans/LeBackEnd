package com.example.l_backend.util;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class ResultSet<T> {
    private int code;
    private String msg;
    private T data;

    public ResultSet(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
