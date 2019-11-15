package com.gpc.service;

import com.gpc.pojo.AttendanceRecord;
import com.gpc.pojo.CheckIn;

import java.util.HashMap;
import java.util.List;

public interface CheckInService {
    //新增签到记录
    CheckIn insertRecord(String id , String data) throws Exception;

    AttendanceRecord queryRecord(String id);

    List<CheckIn> queryCheckInTime(HashMap<String , Object> paramMap);

    List<CheckIn> queryCheckInDay(HashMap<String,Object> paramMap);

    CheckIn queryCheckStatus(HashMap<String, Object> map);

    void task();
}
