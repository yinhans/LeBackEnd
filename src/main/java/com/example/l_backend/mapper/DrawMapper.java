package com.example.l_backend.mapper;

import com.example.l_backend.VO.FolderVO;
import com.example.l_backend.entity.drawing.Folder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DrawMapper {
    @Insert("insert into drawing_folder (_id, name, type, create_at, update_at,owner_id,editor_id) values (#{id},#{name},#{type},now(),now(),#{ownerId},#{editorId})")
    public Integer addFolder(String id, String name, String type, String ownerId, String editorId);

    public FolderVO getFolderById(String id);

    public List<FolderVO> getAllFolders();

    public List<FolderVO> getAllFoldersByOwner(String ownerId);

    public FolderVO getFolderByName(String name);
}
