package com.example.l_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageUploadConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/thumb/**")
                .addResourceLocations("file:C:\\Users\\yinha\\IdeaProjects\\l_backend\\src\\main\\resources\\static\\thumb\\");
        super.addResourceHandlers(registry);
    }
}
