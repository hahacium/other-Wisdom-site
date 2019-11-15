package com.gpc.controller;

import com.gpc.pojo.DepartmentEmployee;
import com.gpc.pojo.Users;
import com.gpc.pojo.vo.UsersVO;
import com.gpc.service.DepartmentEmployeeService;
import com.gpc.service.UserService;
import com.gpc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;
    @Autowired
    private UserService userService;
    @RequestMapping("/labelManager")
    public JsonResult LabelManager() {
        List<DepartmentEmployee> first = departmentEmployeeService.selectFirstLevelLabel();
        return JsonResult.ok(first);
    }
    @RequestMapping("/secondLabelManager")
    public JsonResult secondLabelManager(String id) {
        List<DepartmentEmployee> second = departmentEmployeeService.selectAllSecond(id);
        return JsonResult.ok(second);
    }

    @RequestMapping("personalInformation/{userId}")
    public Users personalInformation(@PathVariable String userId) {
        Users users = userService.queryAllByUserId(userId);
        return users;
    }
}
