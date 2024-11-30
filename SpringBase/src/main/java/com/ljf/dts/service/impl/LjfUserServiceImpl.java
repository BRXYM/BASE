package com.ljf.dts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljf.dts.domain.LjfUser;
import com.ljf.dts.service.LjfUserService;
import com.ljf.dts.mapper.LjfUserMapper;
import com.ljf.dts.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjfUserServiceImpl extends ServiceImpl<LjfUserMapper, LjfUser> implements LjfUserService {
    @Resource
    private LjfUserMapper ljfUserMapper;

    @Override
    public Result getUser() {
        List<LjfUser> list = ljfUserMapper.selectList(null);
        return new Result(200, "查询成功", list, "123");
    }

    @Override
    public Result login(String phone, String password) {
        QueryWrapper<LjfUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ljf_user_phone", phone).eq("ljf_user_psword", password);
        LjfUser user = ljfUserMapper.selectOne(queryWrapper);
        if (user != null) {
            return new Result(200, "登录成功", user, "登录成功");
        } else {
            return new Result(400, "登录失败", null, "用户名或密码错误");
        }
    }

    @Override
    public Result addUser(LjfUser user) {
        boolean save = ljfUserMapper.insert(user) > 0;
        if (save) {
            return new Result(200, "添加成功", user, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result updateUser(LjfUser user) {
        boolean update = ljfUserMapper.updateById(user) > 0;
        if (update) {
            return new Result(200, "更新成功", user, "更新成功");
        } else {
            return new Result(400, "更新失败", null, "更新失败");
        }
    }

    @Override
    public Result deleteUser(Integer id) {
        boolean delete = ljfUserMapper.deleteById(id) > 0;
        if (delete) {
            return new Result(200, "删除成功", null, "删除成功");
        } else {
            return new Result(400, "删除失败", null, "删除失败");
        }
    }

    @Override
    public Result getUserById(Integer id) {
        LjfUser user = ljfUserMapper.selectById(id);
        if (user != null) {
            return new Result(200, "查询成功", user, "查询成功");
        } else {
            return new Result(400, "查询失败", null, "用户不存在");
        }
    }
}
