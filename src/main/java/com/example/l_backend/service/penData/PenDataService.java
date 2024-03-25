package com.example.l_backend.service.penData;


import com.example.l_backend.VO.DataVO;

public interface PenDataService {
    public void addPenData(String id, String data);

    public DataVO getPenDataById(String id);

    public int deleteDataById(String id);

    public int updateDataById(String id, String data);
}
