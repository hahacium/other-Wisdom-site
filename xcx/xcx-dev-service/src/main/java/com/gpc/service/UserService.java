package com.gpc.service;

import com.gpc.pojo.Users;
import com.gpc.pojo.UsersReport;

public interface UserService {
    public Users queryUserForLogin(String username , String password);

    //更新头像
    void updateUserInfo(Users user);

    //个人信息
    Users queryAllByUserId(String userId);

    //保存举报信息
    void reportUser(UsersReport usersReport);
}
