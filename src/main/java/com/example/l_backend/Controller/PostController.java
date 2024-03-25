package com.example.l_backend.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController

public class PostController {


    @GetMapping("/hello")
    @Operation(summary = "测试接口")
    public String helloWorld(@Parameter(name = "name", description = "名称") String name) {
        return "hello" + name;
    }


    //新建图纸文件夹


}
