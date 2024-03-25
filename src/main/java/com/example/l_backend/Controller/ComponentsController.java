package com.example.l_backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.l_backend.entity.component.Directory;
import com.example.l_backend.mapper.DirectoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//组件控制器
@Slf4j
@RestController
public class ComponentsController {
    @Autowired
    Directory directory;
    @Autowired
    DirectoryMapper directoryMapper;

    //添加文件夹
    @PostMapping("/directory/add")
    public Directory addDir(@RequestBody String body) {
        log.info("dirBody" + body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String dirFullPath = jsonObject.getString("fullpath");
        String id = UUID.randomUUID().toString();
        //数据存入数据库
        directory.setId(id);
        directory.setFullPath(dirFullPath);
        directoryMapper.saveDir(directory);
        return directory;
    }
}
