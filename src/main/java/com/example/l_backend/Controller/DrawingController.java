package com.example.l_backend.Controller;


import com.alibaba.fastjson.JSONObject;
import com.example.l_backend.VO.FolderVO;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.VO.ThumbVO;
import com.example.l_backend.service.draw.DrawService;
import com.example.l_backend.service.thumb.ThumbService;
import com.example.l_backend.util.jwt.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
//处理画布的请求
@RestController
public class DrawingController {
    @Autowired
    FolderVO folderVO;
    @Autowired
    DrawService drawService;
    @Autowired
    ThumbService thumbService;
    @Autowired
    JWTUtils jwtUtils;

    //创建图纸文件夹
    @PostMapping("/data/folders/add")
    public FolderVO addFolder(HttpServletRequest request, @RequestBody String folderData) {
        /**
         * {"type":"le5le2d","list":[],"name":"123}
         * name指图纸对应的文件夹名称
         */
        JSONObject object = JSONObject.parseObject(folderData);
        String type = object.getString("type");
//        String list = object.getString("list");
        String name = object.getString("name");
        //生成文件夹id
        String id = UUID.randomUUID().toString();
        /**
         * 查找当前的用户
         * 1、从token中拿到用户名称，去数据库查一下editor id 和username
         * 2、将owner_id和editor_id的id赋值
         */
        LeUserVO userVO = jwtUtils.getUserInfo(request);
        //数据库中插入
        Integer result = drawService.addFolder(id, name, type, userVO.getId(), userVO.getId());
        //从数据库中查找，并且将封装和的值返回给前台
        FolderVO folderVO = drawService.getFolderById(id);
        //对json result要进行封装
        folderVO.setEditor(userVO);
        folderVO.setOwner(userVO);
        return folderVO;
    }

    //获取指定的文件夹
    @PostMapping("/data/folders/get")
    public FolderVO getFolder(@RequestBody String body) {
        //只获取，不更新，更新在update里面
        JSONObject object = JSONObject.parseObject(body);
        String query = object.getString("query");
        JSONObject queryObject = JSONObject.parseObject(query);
        String folderName = queryObject.getString("name");
        String type = queryObject.getString("type");
        //首先找到文件夹的id；
        FolderVO temp = drawService.getFolderByName(folderName);
        //增加thumbVOlist
        List<ThumbVO> thumbVOList = thumbService.getThumbVOsByFId(temp.get_id());
        log.trace(thumbVOList.toString());
        //从数据库中根据name找到对应的folderVO
        FolderVO folderVO = drawService.getFolderByName(folderName);
        folderVO.setList(thumbVOList);
        return folderVO;
    }

    //更新图纸文件夹下的内容
    @PostMapping("/data/folders/update")
    public FolderVO folderUpdate(HttpServletRequest request, @RequestBody String body) {
        LeUserVO userVO = jwtUtils.getUserInfo(request);
        //更新editor信息，返回给前台 editor id username updateAt
        JSONObject object = JSONObject.parseObject(body);
        //拿到的是文件夹对应的id
        String fid = object.getString("_id");
        List jsonArray = object.getJSONArray("list");
//        thumbService.deleteThumbNoFId();
        List<ThumbVO> thumbVOList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            //给每个截图增加name,component
            JSONObject object1 = JSONObject.parseObject(jsonArray.get(i).toString());
            String imgUrl = object1.getString("image");
            String component = object1.getString("component");
            String name = object1.getString("name");
            String imgId = object1.getString("id");
            //更新一下thumb表的name字段和folderID
            thumbService.update(imgId, fid, imgUrl, name, Boolean.getBoolean(component));
//            ThumbVO thumbVO = thumbService.getThumbVOByName(imageUrl);
            ThumbVO thumbVO = thumbService.getThumbVOById(imgId);
            thumbVOList.add(thumbVO);
        }
        //拿list 插进去
        //返回folderVO
        //1、根据_id查找folder，然后将数据更新至folder中
        folderVO = drawService.getFolderById(fid);
        folderVO.setList(thumbVOList);
        folderVO.setEditor(userVO);
        folderVO.setOwner(userVO);
        return folderVO;
    }

    //获取图纸文件夹下面的内容
    @PostMapping(value = {"/data/le5le2d/list", "/data/le5le2d-components/list"})
    //返回的数据类型 {list:[],total:0}
    //{_id:folderId image:url name:name}
    public Map<String, Object> thumbList() {
        Map<String, Object> resultMap = new HashMap<>();
        //1、先查找component=flase 的所有thumbVO
        List<ThumbVO> thumbVOList = thumbService.getAllThumbVOs();
        log.info(thumbVOList.toString());
        List<Map<String, String>> thumbResultList = new ArrayList<>();
        Map<String, String> thumbResultMap = new HashMap<>();
        for (ThumbVO thumbVO : thumbVOList) {
            thumbResultMap.put("image", thumbVO.getImage());
            thumbResultMap.put("name", thumbVO.getName());
            thumbResultMap.put("_id", thumbVO.getId());
            thumbResultList.add(thumbResultMap);
        }
        //list image name _id
        resultMap.put("list", thumbResultList);
        resultMap.put("total", thumbVOList.size());
        return resultMap;
    }

    @PostMapping("/data/folders/list")
    //略缩图下的所有文件从这个接口取
    public Map<String, Object> folderList(HttpServletRequest request) {
        LeUserVO leUserVO = jwtUtils.getUserInfo(request);
        Map<String, Object> resultMap = new HashMap<>();
        List<FolderVO> folderVOS = drawService.getAllFolders();
        for (FolderVO folderVO : folderVOS) {
            //查找文件夹对应的画布
            List<ThumbVO> thumbVOList = thumbService.getThumbVOsByFId(folderVO.get_id());
            folderVO.setList(thumbVOList);
            folderVO.setOwner(leUserVO);
            folderVO.setEditor(leUserVO);
        }
        resultMap.put("list", folderVOS);
        resultMap.put("total", folderVOS.size());
        return resultMap;
    }
}
