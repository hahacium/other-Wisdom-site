package com.gpc.service.impl;

import com.gpc.mapper.DepartmentEmployeeMapper;
import com.gpc.pojo.DepartmentEmployee;
import com.gpc.service.DepartmentEmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {
    @Autowired
    private DepartmentEmployeeMapper departmentEmployeeMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<DepartmentEmployee> selectFirstLevelLabel() {
        Example example = new Example(DepartmentEmployee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("pid");
        return departmentEmployeeMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<DepartmentEmployee> selectAllSecond(String id) {
        if(StringUtils.isBlank(id)){
            return departmentEmployeeMapper.selectAll();
        }
        return departmentEmployeeMapper.selectAllSecond(id);
    }
}
