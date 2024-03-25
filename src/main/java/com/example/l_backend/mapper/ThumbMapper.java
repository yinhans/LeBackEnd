package com.example.l_backend.mapper;

import com.example.l_backend.VO.ThumbVO;
import com.example.l_backend.entity.thumb.Thumb;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ThumbMapper {
    public void saveThumb(Thumb thumb);

    public ThumbVO getThumbVOById(String id);

    public ThumbVO getThumbVOByName(String name);

    //    根据略缩图文件名查找
    @Select("select name, url, component, id from thumb where filename=#{fileName}")
    public ThumbVO getThumbVOByFileName(String fileName);

    @Update("update thumb set url=#{url},folder_id=#{fid},name=#{name},component=#{component} where id=#{id}")
    public int update(String id, String fid, String url, String name, boolean component);

    @Update("update thumb set url=#{url} where id=#{id}")
    public int updateUrlById(String url, String id);

    public List<ThumbVO> getAllThumbVOs();

    //根据文件夹的id查找所有的thumbVO
    public List<ThumbVO> getThumbVOsByFId(@Param("fId") String fId);

    @Select("select id from thumb where url=#{url}")
    public String getThumbIdByUrl(String url);

    //根据略缩图的文件名删除
    @Delete("delete from thumb where filename=#{filename}")
    public int deleteThumbByFileName(String filename);

    @Delete("delete from thumb where folder_id is null")
    public int deleteThumbNoFId();
}
