package com.example.l_backend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.l_backend.VO.DataVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PenDataMapper {
    @Insert("insert into pendata (_id,data) values (#{id},#{data})")
    public void addPenData(String id, String data);

    @Select("select _id,data from pendata where _id=#{id}")
    public DataVO getPendataById(String id);

    @Delete("delete from pendata where _id=#{id}")
    public int deleteDataById(String id);

    @Update("update pendata set data = #{data} where _id=#{id}")
    public int updateDataById(String id, String data);
}
