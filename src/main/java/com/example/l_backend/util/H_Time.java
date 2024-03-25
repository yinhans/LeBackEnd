package com.example.l_backend.util;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class H_Time {
    //获取到年月日秒
    public String getTime(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStr = df.format(localDateTime);
        return timeStr;
    }

    public String getDate(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateStr = df.format(localDateTime);
        return dateStr;
    }
}
