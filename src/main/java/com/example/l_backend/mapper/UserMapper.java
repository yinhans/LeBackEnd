package com.example.l_backend.mapper;

import com.example.l_backend.DTO.LeUserDTO;
import com.example.l_backend.VO.LeUserVO;
import com.example.l_backend.entity.user.LeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    public LeUserVO getUserVOByName(String username);

    @Select("select username,password from user where username=#{username}")
    public LeUserDTO getByUserName(String username);

    @Select("select account,username,remember from user")
    public List<LeUserVO> getUserVOs();
}
