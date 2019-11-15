package com.gpc.service.impl;

import com.gpc.mapper.UsersMapper;
import com.gpc.mapper.UsersReportMapper;
import com.gpc.pojo.Users;
import com.gpc.pojo.UsersReport;
import com.gpc.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersReportMapper usersReportMapper;
    @Autowired
    private Sid sid;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username" , username);
        criteria.andEqualTo("password",password);
        Users users = usersMapper.selectOneByExample(example);
        return users;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserInfo(Users user) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id",user.getId());
        usersMapper.updateByExampleSelective(user,userExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryAllByUserId(String userId) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",userId);
        return usersMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void reportUser(UsersReport usersReport) {
        String id = sid.nextShort();
        usersReport.setId(id);
        usersReport.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        usersReportMapper.insert(usersReport);
    }
}
