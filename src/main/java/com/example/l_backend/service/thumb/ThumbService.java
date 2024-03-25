package com.example.l_backend.service.thumb;

import com.example.l_backend.VO.ThumbVO;
import com.example.l_backend.entity.thumb.Thumb;

import java.util.List;


public interface ThumbService {
    public void saveThumb(Thumb thumb);

    public ThumbVO getThumbVOById(String id);

    public ThumbVO getThumbVOByName(String name);

    public ThumbVO getThumbVOByFileName(String fileName);

    public int update(String id, String fid, String url, String name, boolean component);

    public List<ThumbVO> getAllThumbVOs();

    public List<ThumbVO> getThumbVOsByFId(String fId);

    public String getThumbIdByUrl(String url);

    public int deleteThumbByFileName(String filename);

    public int updateUrlById(String url, String id);

    public int deleteThumbNoFId();
}
