package com.example.l_backend.entity.thumb;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Data
public class Thumb {
    private String id;
    private String fileName;
    private String url;
    private String directory;
    private String name;
    private String remarks;
    private boolean shared;
    private long size;
    private List<String> tags;
    private String type;
    private String userName;
    private String userId;
}
