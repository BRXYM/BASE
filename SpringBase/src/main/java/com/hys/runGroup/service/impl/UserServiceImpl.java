package com.hys.runGroup.service.impl;

import com.hys.runGroup.domain.User;
import com.hys.runGroup.mapper.UserMapper;
import com.hys.runGroup.service.UserService;
import com.hys.runGroup.utils.Result;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public Result getAllUsers() {
        List<User> list = userMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return new Result(200, "001", list, "获取成功");
        } else {
            return new Result(200, "002", list, "列表为空");
        }
    }

    @Override
    public Result addUser(User user) {
        Integer i = userMapper.insert(user);
        if (i > 0) {
            return new Result(200, "001", i, "添加成功");
        }else {
            return new Result(200, "002", i, "添加失败");
        }
    }

    @Override
    public Result deleteUser(User user) {
        User newUser = userMapper.selectById(user.getUid());
        if (newUser == null) {
            return new Result(200,"003",user,"对象不存在");
        }
        Integer i = userMapper.deleteById(user.getUid());
        if (i > 0) {
            return new Result(200,"001",i,"删除成功");
        }else {
            return new Result(200,"002",i,"删除失败");
        }
    }

    @Override
    public Result updateUser(User user) {
        User updateUser = userMapper.selectById(user.getUid());
        if (updateUser == null) {
            return new Result(200,"003",user,"对象不存在");
        }
        try{
            userMapper.updateById(user);
        }catch (Exception ignored) {}
        if (userMapper != null) {
            return new Result(200,"001",user,"修改成功");
        } else {
            return new Result(200,"002",user,"修改失败");
        }
    }

    @Override
    public Result loginUser(User user) {
        User login = userMapper.selectById(user.getUid());
        if (login == null) {
            return new Result(200,"003",user,"用户id错误");
        }
        if (login.getUpass().equals(user.getUpass())) {
            return new Result(200,"003",user,"用户id错误");
        } else {
            return new Result(200,"002",user,"密码错误");
        }
    }
}
