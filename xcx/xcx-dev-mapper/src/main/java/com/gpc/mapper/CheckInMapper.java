package com.gpc.mapper;

import com.gpc.pojo.CheckIn;
import com.gpc.utils.MyMapper;

import java.util.HashMap;
import java.util.List;

public interface CheckInMapper extends MyMapper<CheckIn> {
    List<CheckIn> selectById(String id);


    List<CheckIn> queryCheckInTime(HashMap<String , Object> paramMap);

    List<CheckIn> queryCheckInDay(HashMap<String,Object> paramMap);

    CheckIn queryCheckStatus(HashMap<String, Object> map);
}