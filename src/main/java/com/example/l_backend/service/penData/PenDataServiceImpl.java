package com.example.l_backend.service.penData;

import com.alibaba.fastjson.JSONObject;
import com.example.l_backend.VO.DataVO;
import com.example.l_backend.mapper.PenDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenDataServiceImpl implements PenDataService {
    @Autowired
    PenDataMapper penDataMapper;

    @Override
    public void addPenData(String id, String data) {
        penDataMapper.addPenData(id, data);
    }

    @Override
    public DataVO getPenDataById(String id) {
        return penDataMapper.getPendataById(id);
    }

    @Override
    public int deleteDataById(String id) {
        return penDataMapper.deleteDataById(id);
    }

    @Override
    public int updateDataById(String id, String data) {
        return penDataMapper.updateDataById(id, data);
    }
}
