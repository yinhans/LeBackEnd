package com.example.l_backend.service.user;

import com.example.l_backend.DTO.LeUserDTO;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.entity.user.LeUser;

import java.util.List;

public interface UserService {
    public LeUserVO getUserVOByName(String username);

    public LeUserDTO getByUserName(String username);

    public List<LeUserVO> getUsersVO();
}
