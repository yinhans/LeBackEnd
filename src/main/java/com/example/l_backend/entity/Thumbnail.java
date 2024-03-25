package com.example.l_backend.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
//前端回传的略缩图实体类
@Data
public class Thumbnail {
    //文件名
    String fileName;
    //存储路径
    String directory;
}
