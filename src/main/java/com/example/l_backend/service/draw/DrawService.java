package com.example.l_backend.service.draw;

import com.example.l_backend.VO.FolderVO;

import java.util.List;

public interface DrawService {
    public Integer addFolder(String id, String name, String type, String ownerId, String editorId);

    public List<FolderVO> getAllFolders();

    public FolderVO getFolderById(String id);

    public FolderVO getFolderByName(String name);
}
