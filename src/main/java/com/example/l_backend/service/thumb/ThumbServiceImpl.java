package com.example.l_backend.service.thumb;

import com.example.l_backend.VO.ThumbVO;
import com.example.l_backend.entity.thumb.Thumb;
import com.example.l_backend.mapper.ThumbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThumbServiceImpl implements ThumbService {
    @Autowired
    ThumbMapper thumbMapper;

    @Override
    public void saveThumb(Thumb thumb) {
        thumbMapper.saveThumb(thumb);
    }

    @Override
    public ThumbVO getThumbVOByName(String name) {
        return thumbMapper.getThumbVOByName(name);
    }

    @Override
    public ThumbVO getThumbVOById(String id) {
        return thumbMapper.getThumbVOById(id);
    }

    @Override
    public int update(String id, String fid, String url, String name, boolean component) {
        return thumbMapper.update(id, fid, url, name, component);
    }

    @Override
    public List<ThumbVO> getAllThumbVOs() {
        return thumbMapper.getAllThumbVOs();
    }

    @Override
    public List<ThumbVO> getThumbVOsByFId(String fId) {
        return thumbMapper.getThumbVOsByFId(fId);
    }

    @Override
    public String getThumbIdByUrl(String url) {
        return thumbMapper.getThumbIdByUrl(url);
    }

    @Override
    public ThumbVO getThumbVOByFileName(String fileName) {
        return thumbMapper.getThumbVOByFileName(fileName);
    }

    @Override
    public int deleteThumbByFileName(String filename) {
        return thumbMapper.deleteThumbByFileName(filename);
    }

    @Override
    public int updateUrlById(String url, String id) {
        return thumbMapper.updateUrlById(url, id);
    }

    @Override
    public int deleteThumbNoFId() {
        return thumbMapper.deleteThumbNoFId();
    }
}
