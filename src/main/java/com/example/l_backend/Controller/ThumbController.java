package com.example.l_backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.VO.ThumbVO;
import com.example.l_backend.entity.thumb.Thumb;
import com.example.l_backend.service.penData.PenDataService;
import com.example.l_backend.util.jwt.JWTUtils;
import com.example.l_backend.util.thumb.ThumbUtil;
import com.example.l_backend.service.thumb.ThumbService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController

public class ThumbController {
    @Autowired
    ThumbUtil thumbUtil;
    @Autowired
    Thumb thumb;
    @Autowired
    ThumbService thumbService;
    @Autowired
    PenDataService penDataService;
    @Autowired
    JWTUtils jwtUtils;


    //略缩图上传
    @PostMapping("/image/upload")
    public JSONObject uploadThumb(MultipartHttpServletRequest request) {
        /*
         * 返回的数据类型
         * filename
         * metadata:{
         *   directory,name,remarks,shared,size,tags,type,userId,username
         * }
         * url
         */
        //1、保存略缩图文件并返回文件路径
        String fileName = thumbUtil.save(request);
        String url = thumbUtil.getUrl() + fileName;
        //2、获取user信息
        LeUserVO leUserVO = jwtUtils.getUserInfo(request);
        String id = UUID.randomUUID().toString();
        JSONObject result = JSONObject.parseObject("{}");
        JSONObject metaData = JSONObject.parseObject("{}");
        metaData.put("directory", request.getParameter("directory"));
        metaData.put("shared", request.getParameter("share"));
        metaData.put("size", thumbUtil.getSize());
        metaData.put("userId", leUserVO.getId());
        metaData.put("username", leUserVO.getUsername());
        result.put("metadata", metaData);
        //2、装载数据
        result.put("filename", fileName);
        result.put("url", url);
        thumb.setId(id);
        thumb.setUrl(url);
        thumb.setFileName(fileName);
        //3、保存进数据库
        thumbService.saveThumb(thumb);
        return result;
    }

    @DeleteMapping("/{fileName}")
    public HashMap<Object, Object> deleteThumb(@PathVariable String fileName) {
        System.out.println(fileName);
//        ThumbVO thumbVO = thumbService.getThumbVOByFileName(fileName);
        //先直接删除记录（不能删除记录，前端要知道修改哪张图片）
        //这里空接口，实际上可以把实体文件删掉，数据库记录保持不变，前端下一个getfolder请求需要知道以前的文件夹记录
//        thumbService.deleteThumbByFileName(fileName);
        return new HashMap<>();
    }
}
