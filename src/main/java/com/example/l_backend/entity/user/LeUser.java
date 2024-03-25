package com.example.l_backend.entity.user;

import lombok.Data;

@Data
public class LeUser {
    private String _id;
    private String pid;
    private String name;
    private String account;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String code;
    private String remember;
}
