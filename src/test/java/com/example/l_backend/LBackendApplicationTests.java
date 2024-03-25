package com.example.l_backend;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.l_backend.VO.FolderVO;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.service.draw.DrawService;
import com.example.l_backend.service.user.UserService;
import com.example.l_backend.util.H_Time;
import com.example.l_backend.util.jwt.JWTUtils;
import com.example.l_backend.util.thumb.ThumbUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LBackendApplicationTests {
    @Autowired
    private H_Time hTime;
    @Autowired
    ThumbUtil thumbUtil;
    @Autowired
    DrawService drawService;
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    void testTime() {
        System.out.println(hTime.getTime());
        System.out.println(hTime.getDate());
    }

    @Test
    void getToken() {
        System.out.println(new JWTUtils().createToken("hans"));
    }

    @Test
    void getUser() {
        LeUserVO leUserVO = userService.getUserVOByName("admin");
        JSONObject j = JSONObject.parseObject("{}");
        j.put("user", null);
        System.out.println(j);
    }

    @Test
    void getFolder() {
        FolderVO folderVO = drawService.getFolderByName("12345");
        System.out.println(folderVO);
    }

}
