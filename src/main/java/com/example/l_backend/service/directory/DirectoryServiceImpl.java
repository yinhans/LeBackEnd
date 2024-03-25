package com.example.l_backend.service.directory;

import com.example.l_backend.entity.component.Directory;
import com.example.l_backend.mapper.DirectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    @Autowired
    DirectoryMapper directoryMapper;

    @Override
    public int saveDir(Directory directory) {
        return directoryMapper.saveDir(directory);
    }
}
