package com.example.l_backend.service.draw;

import com.example.l_backend.VO.FolderVO;
import com.example.l_backend.mapper.DrawMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrawServiceImpl implements DrawService {
    @Autowired
    DrawMapper drawMapper;

    @Override
    public Integer addFolder(String id, String name, String type, String ownerId, String editorId) {
        return drawMapper.addFolder(id, name, type, ownerId, editorId);
    }

    @Override
    public List<FolderVO> getAllFolders() {
        return drawMapper.getAllFolders();
    }

    @Override
    public FolderVO getFolderById(String id) {
        return drawMapper.getFolderById(id);
    }

    @Override
    public FolderVO getFolderByName(String name) {
        return drawMapper.getFolderByName(name);
    }
}
