package com.gpc.service;

import com.gpc.pojo.DepartmentEmployee;
import com.gpc.pojo.vo.DepartmentEmployeeVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentEmployeeService {
    /**
     * 查询所有一级分类
     */
    public List<DepartmentEmployee> selectFirstLevelLabel();

    /**
     * 根据一级栏目查询二级栏目
     */
    public List<DepartmentEmployee> selectAllSecond(String id);
}
