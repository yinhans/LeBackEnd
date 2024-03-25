package com.example.l_backend.service.user;

import com.example.l_backend.DTO.LeUserDTO;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.entity.user.LeUser;
import com.example.l_backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public LeUserVO getUserVOByName(String username) {
        return userMapper.getUserVOByName(username);
    }

    @Override
    public LeUserDTO getByUserName(String username) {
        return userMapper.getByUserName(username);
    }

    @Override
    public List<LeUserVO> getUsersVO() {
        List<LeUserVO> leUserVOS = userMapper.getUserVOs();
        return leUserVOS;
    }
}
