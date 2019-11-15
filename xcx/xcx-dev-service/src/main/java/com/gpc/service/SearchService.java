package com.gpc.service;

import com.gpc.pojo.Users;

import java.util.List;

public interface SearchService {
    /**
     * 搜索匹配
     */
    public List<String> searchMatching();


    List<Users> selectByName(String name);
}
