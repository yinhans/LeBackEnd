package com.example.l_backend.VO;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class ThumbVO {
    private String image;
    private String name;
    private boolean component;
    private String _id;//对应文件夹的id
    private String id;//略缩图自身的id
    private LeUserVO editor;
    private LeUserVO owner;
}
