package com.example.l_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JWTInterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> jwtExcludePatterns = new ArrayList<>();
        jwtExcludePatterns.add("/login");
        jwtExcludePatterns.add("/thumb/**");
        registry.addInterceptor(jwtInterceptor())//拦截器规则
                .addPathPatterns("/**")//拦截所有的请求路径
                .excludePathPatterns(jwtExcludePatterns);//放行登录接口,放行静态资源
        super.addInterceptors(registry);
    }

    @Bean
    public JWTInterceptor jwtInterceptor() {
        return new JWTInterceptor();
    }
}
