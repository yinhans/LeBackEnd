package com.example.l_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.l_backend.mapper")
public class LBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LBackendApplication.class, args);

    }
}
