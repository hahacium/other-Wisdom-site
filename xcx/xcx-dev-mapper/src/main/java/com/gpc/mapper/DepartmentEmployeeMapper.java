package com.gpc.mapper;

import com.gpc.pojo.DepartmentEmployee;
import com.gpc.utils.MyMapper;

import java.util.List;

public interface DepartmentEmployeeMapper extends MyMapper<DepartmentEmployee> {
    public List<DepartmentEmployee> selectAllSecond(String id) ;

    public List<DepartmentEmployee> selectAll();
}