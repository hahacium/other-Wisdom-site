package com.gpc.mapper;

import com.gpc.pojo.Users;
import com.gpc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper extends MyMapper<Users> {
    List<Users> selectByName(@Param("name") String name);
}