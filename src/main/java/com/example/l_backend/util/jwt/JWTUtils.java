package com.example.l_backend.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.service.user.UserService;
import com.example.l_backend.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTUtils {
    @Autowired
    UserService userService;
    public static final String sign = "HANS";

    public static String createToken(String username) {
//        Calendar instance = Calendar.getInstance();
        //令牌过期时间
        // instance.add(Calender.DATE,7);
        return JWT.create().withClaim("username", username)
                .sign(Algorithm.HMAC256(sign));
    }

    public static Map<String, String> verifyToken(String token) {
        //创建
        Map<String, String> resultMap = new HashMap<>();
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        String username = String.valueOf(verify.getClaim("username"));
        resultMap.put("username", username);
        return resultMap;
        //TODO 过期时间
    }

    public LeUserVO getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, String> tokenMap = JWTUtils.verifyToken(token);
        String username = tokenMap.get("username").trim().replace("\"", "");
        log.info(username);
        //数据库查询 id,username,account
        log.info(userService.getUserVOByName(username).getUsername());
        return userService.getUserVOByName(username);
    }
}
