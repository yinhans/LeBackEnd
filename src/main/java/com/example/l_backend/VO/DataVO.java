package com.example.l_backend.VO;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Data
@Repository
public class DataVO {
    String id;
    String data;
    Timestamp updateAt;
}
