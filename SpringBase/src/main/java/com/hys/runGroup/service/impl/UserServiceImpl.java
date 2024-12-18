package com.hys.runGroup.service.impl;

import com.hys.runGroup.domain.User;
import com.hys.runGroup.mapper.UserMapper;
import com.hys.runGroup.service.UserService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户表实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result getAllUsers() {
        List<User> list = userMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list,"获取成功");
//            return new Result(200, "001", list, "获取成功");
        } else {
            return Result.fail("列表为空");
//            return new Result(200, "002", list, "列表为空");
        }
    }

    @Override
    public Result addUser(User user) {
        int i = userMapper.insert(user);
        if (i > 0) {
            return Result.success(userMapper.selectById(user.getUid()),"添加成功");
//            return new Result(200, "001", userMapper.selectById(user.getUid()), "添加成功");
        }else {
            return Result.fail("添加失败");
//            return new Result(200, "002", user, "添加失败");
        }
    }

    @Override
    public Result deleteUser(User user) {
        User newUser = userMapper.selectById(user.getUid());
        if (newUser == null) {
            return Result.fail("对象不存在");
//            return new Result(200,"003",user,"对象不存在");
        }
        Integer i = userMapper.deleteById(user.getUid());
        if (i > 0) {
            return Result.success(i,"删除成功");
//            return new Result(200,"001",i,"删除成功");
        }else {
            return Result.fail("删除失败");
//            return new Result(200,"002",i,"删除失败");
        }
    }

    @Override
    public Result updateUser(User user) {
        User updateUser = userMapper.selectById(user.getUid());
        if (updateUser == null) {
            return Result.fail("对象不存在");
//            return new Result(200,"003",user,"对象不存在");
        }
        try{
            userMapper.updateById(user);
        }catch (Exception ignored) {}
        if (userMapper != null) {
            return Result.success(userMapper.selectById(user.getUid()),"修改成功");
//            return new Result(200,"001",user,"修改成功");
        } else {
            return Result.fail("修改失败");
//            return new Result(200,"002",user,"修改失败");
        }
    }

    @Override
    public Result loginUser(User user) {
        User login = userMapper.selectById(user.getUid());
        if (login == null) {
            return Result.fail("用户id错误");
//            return new Result(200,"003",user,"用户id错误");
        }
        if (login.getUpass().equals(user.getUpass())) {
            return Result.success(login,"登陆成功");
//            return new Result(200,"001",login,"登陆成功");
        } else {
            return Result.fail("密码错误");
//            return new Result(200,"002",user,"密码错误");
        }
    }
}
