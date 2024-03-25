package com.example.l_backend.config;


import com.example.l_backend.util.jwt.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器方法");
//        String token = request.getHeader("Authorization");
//        if (token == null) {
//            log.info(request.getRequestURI().trim());
//            String thumbUrl = request.getRequestURI().trim().split("/")[0];
//            log.info(thumbUrl);
//            return thumbUrl.equals("thumb");
//        }
//        //获取UserId
//        Map<String, String> tokenMap = JWTUtils.verifyToken(token);
//        String username = tokenMap.get("username");
        return true;
    }
}
