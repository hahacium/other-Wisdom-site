package com.gpc.service.impl;

import com.gpc.mapper.UsersMapper;
import com.gpc.pojo.Users;
import com.gpc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> searchMatching() {
        List<String> searchList = new ArrayList<>();
        List<Users> users = usersMapper.selectAll();
        for(Users user : users) {
            String name = user.getName();
            searchList.add(name);
        }
        return searchList;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Users> selectByName(String name) {
        List<Users> users = usersMapper.selectByName(name);
        return users;
    }
}
