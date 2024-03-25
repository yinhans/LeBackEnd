package com.example.l_backend.entity.drawing;

import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.VO.ThumbVO;
import com.example.l_backend.entity.thumb.Thumb;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Data
public class Folder {
    private String _id;
    private String name;
    private LeUserVO editor;
    private LeUserVO owner;
    private Timestamp createAt;
    private Timestamp updateAt;


}
