package com.hys.runGroup.service;

import com.hys.runGroup.domain.User;
import com.hys.runGroup.utils.Result;


/**
 * 用户表服务层接口
 */
public interface UserService {

//    获取所有用户
    Result getAllUsers();
//    添加用户
    Result addUser(User user);
//    删除用户
    Result deleteUser(int uid);

    // 修改用户
    Result updateUser(User user);
//    登录
    Result loginUser(User user);
}
