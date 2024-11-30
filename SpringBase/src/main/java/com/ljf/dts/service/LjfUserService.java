package com.ljf.dts.service;

import com.ljf.dts.domain.LjfUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.dts.utils.Result;

import java.util.List;

public interface LjfUserService extends IService<LjfUser> {
    Result getUser();
    Result login(String phone, String password);
    Result addUser(LjfUser user);
    Result updateUser(LjfUser user);
    Result deleteUser(Integer id);
    Result getUserById(Integer id);
}
