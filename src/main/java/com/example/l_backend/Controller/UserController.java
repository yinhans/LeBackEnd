package com.example.l_backend.Controller;

import com.example.l_backend.DTO.LeUserDTO;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.entity.user.LeUser;
import com.example.l_backend.service.user.UserService;
import com.example.l_backend.util.ResultSet;
import com.example.l_backend.util.jwt.JWTUtils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
//处理用户的相关请求
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    LeUserDTO userDTO;
    @Autowired
    LeUserVO leUserVO;

    //前端使用x-www-form-urlencoded
    @PostMapping("/create")
    public String createUser(@RequestBody String userBody) {
        //和官网给的数据类型不太一样 type:le5le2d list name
        return "ok";
    }

    @PostMapping("/login")
    public ResultSet<LeUserVO> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //处理登录的逻辑
        //1、获取用户登录的用户名和密码 username,password
        //2、从数据库中查询用户信息，判断密码是否一致
        //3、将数据库中查询到的一部分信息装载入UserDTO，将userDTO数据返给前端
        log.info(username);
        log.info(password);
        userDTO = userService.getByUserName(username);
        if (userDTO == null) {
            ResultSet<LeUserVO> resultSet = new ResultSet(400, "用户名不存在", null);
            return resultSet;
        } else {
            if (!Objects.equals(userDTO.getPassword(), password)) {
                return new ResultSet<>(200, "密码错误", null);
            }
        }
        String token = JWTUtils.createToken(userDTO.getUsername());
        leUserVO.setUsername(userDTO.getUsername());
        leUserVO.setToken(token);
        return new ResultSet<LeUserVO>(200, "登录成功", leUserVO);
    }
    

    //处理用户登录跳转后携带token头的信息，将用户数据返给画布前端
    @GetMapping("/account/profile")
    public LeUserDTO getUser(@RequestHeader("Authorization") String token) {
        //TODO 判断token是否为空
        //1、首先对token数据进行检查
        Map<String, String> tokenMap = JWTUtils.verifyToken(token);
        //2、解析出token中的username，根据username进入数据库查找用户信息
        String username = tokenMap.get("username").trim().replace("\"", "");
        LeUserDTO leUserDTO = userService.getByUserName(username);
        //3、封装用户信息，返回给前端
        return leUserDTO;
    }

    @PostMapping("/users")
    public ResultSet<List<LeUserVO>> getUsers() {
        List<LeUserVO> leUserVOs = null;
        ResultSet<List<LeUserVO>> resultSet = new ResultSet<>(400, "操作失败", null);
        try {
            leUserVOs = userService.getUsersVO();
            if (leUserVOs != null) {
                resultSet.setCode(200);
                resultSet.setMsg("请求成功");
                resultSet.setData(leUserVOs);
            } else {
                return resultSet;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
