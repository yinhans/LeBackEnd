package com.example.l_backend.entity.component;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
@Data
public class Directory {
    private String id;
    private String uId;
    private String fullPath;
    private Timestamp updateAt;
    private Timestamp deleteAt;
    private Timestamp createdAt;
}
