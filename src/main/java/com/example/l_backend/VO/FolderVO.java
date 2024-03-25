package com.example.l_backend.VO;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Data
@Repository
public class FolderVO {
    private String _id;
    private String name;
    private Timestamp createAt;
    private Timestamp updateAt;
    private LeUserVO owner;
    private LeUserVO editor;
    private String type;
    private List<ThumbVO> list;
}
