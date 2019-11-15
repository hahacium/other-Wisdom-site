package com.gpc.controller;

import com.gpc.pojo.Users;
import com.gpc.pojo.vo.UsersVO;
import com.gpc.service.UserService;
import com.gpc.utils.JsonResult;
import com.gpc.utils.MD5Utils;
import com.gpc.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class RegistLoginController extends BasicController{
    @Autowired
    private UserService userService;
    @Autowired
    private RedisOperator redisOperator;

    public UsersVO setUserRedisSessionToken(Users userModel){
        String uniqueToken = UUID.randomUUID().toString();
        redisOperator.set(USER_REDIS_SESSION+":"+userModel.getId(),uniqueToken,1000*60*30);
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(userModel,usersVO);
        usersVO.setUserToken(uniqueToken);
        return usersVO;
    }

    //登陆
    @RequestMapping("/login")
    public JsonResult login(@RequestBody Users user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        //判断用户名和密码不为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JsonResult.errorMsg("用户名和密码不能为空...");
        }
        // 2. 判断用户名密码
        Users userResult = userService.queryUserForLogin(username,MD5Utils.getMD5Str(password));
        //返回
        if(userResult != null){
            userResult.setPassword("");
            UsersVO usersVO = setUserRedisSessionToken(userResult);
            return JsonResult.ok(usersVO);
        }else{
            return JsonResult.errorMsg("用户名或密码不正确, 请重试...");
        }
    }

    //注销
    @PostMapping("/logout/{userId}")
    public JsonResult logout(@PathVariable String userId) throws Exception {
        Thread.sleep(500);
        redis.del(USER_REDIS_SESSION + ":" + userId);
        return JsonResult.ok();
    }
}
