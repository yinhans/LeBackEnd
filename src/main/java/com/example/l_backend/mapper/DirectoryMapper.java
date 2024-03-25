package com.example.l_backend.mapper;

import com.example.l_backend.entity.component.Directory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DirectoryMapper {
    //存文件（插入）
    public int saveDir(Directory directory);
}
