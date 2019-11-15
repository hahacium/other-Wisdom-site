package com.gpc.mapper;

import com.gpc.pojo.AttendanceRecord;
import com.gpc.utils.MyMapper;

import java.util.List;

public interface AttendanceRecordMapper extends MyMapper<AttendanceRecord> {
    List<AttendanceRecord> selectByUserId(String id);
}