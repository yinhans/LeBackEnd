package com.example.l_backend.util.jsonUtils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class LJsonUtils {
    //生成简单的json字段
    //{a:123,b:456}
    public static JSONObject simpleJsonGenerator(Map<String, Object> dataMap) {
        return new JSONObject(dataMap);
    }
}
