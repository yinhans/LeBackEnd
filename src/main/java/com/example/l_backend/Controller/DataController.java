package com.example.l_backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.l_backend.VO.DataVO;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.service.penData.PenDataService;
import com.example.l_backend.service.thumb.ThumbService;
import com.example.l_backend.util.jwt.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DataController {
    @Autowired
    PenDataService penDataService;
    @Autowired
    ThumbService thumbService;
    @Autowired
    JWTUtils jwtUtils;

    @PostMapping(value = {"/data/le5le2d/add", "/data/le5le2d-components/add"})
    public JSONObject add(HttpServletRequest request, @RequestBody String jsonString) {
        //获取user信息
        LeUserVO leUserVO = jwtUtils.getUserInfo(request);
        LeUserVO leUserVO1 = jwtUtils.getUserInfo(request);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //获取image里面的imagUrl ，然后去数据库里面把imageUrl的id 查出来，然后在装载给dataVO
        String imageUrl = jsonObject.getString("image");
        //给画布json数据添加thumb的id并存入数据库
        String id = thumbService.getThumbIdByUrl(imageUrl);
        //给画布数据库中添加id
        jsonObject.put("_id", id);
        //给画布数据库中增加user和editor信息
        jsonObject.put("editor", leUserVO);
        jsonObject.put("owner", leUserVO1);
        //存入数据库
        String resultStr = JSONObject.toJSONString(jsonObject);
        penDataService.addPenData(id, resultStr);
        //根据id查找数据库
        DataVO dataVO = penDataService.getPenDataById(id);
        String data = dataVO.getData();
        JSONObject result = JSONObject.parseObject(data);
        return result;
    }

    @PostMapping(value = {"/data/le5le2d/get", "/data/le5le2d-components/get"})
    public JSONObject get(@RequestBody String body) {

        JSONObject jsonObject = JSONObject.parseObject(body);
        String id = jsonObject.getString("id");
        //pendata中找数据
        DataVO dataVO = penDataService.getPenDataById(id);
        String data = dataVO.getData();
        JSONObject result = JSONObject.parseObject(data);
        return result;
    }

    @PostMapping("/data/le5le2d/update")
    public JSONObject update(@RequestBody String body) {
        //找到老数据，更新新数据
        JSONObject jsonObject = JSONObject.parseObject(body);
        //老图片的id
        String _id = jsonObject.getString("_id");
        //新图片的url
        String imageUrl = jsonObject.getString("image");
        //数据库根据imageUrl更新新图片Id
        //将新图片的_id更新为之前的id
        penDataService.updateDataById(_id, body);
//        thumbService.updateIdByUrl(imageUrl, _id);  update 以前id的url updateUrlByid
        thumbService.updateUrlById(imageUrl, _id);
        DataVO dataVO = penDataService.getPenDataById(_id);
        String data = dataVO.getData();
        JSONObject result = JSONObject.parseObject(data);
        return result;
    }
}
