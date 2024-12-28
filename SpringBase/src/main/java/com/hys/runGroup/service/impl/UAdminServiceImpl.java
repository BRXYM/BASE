package com.hys.runGroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hys.runGroup.domain.UAdmin;
import com.hys.runGroup.mapper.UAdminMapper;
import com.hys.runGroup.service.UAdminService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UAdminServiceImpl implements UAdminService {
    @Resource
    private UAdminMapper uAdminMapper;

    /**
     * 管理员登录
     * @param uAdmin - 包含用户名和密码的管理员对象
     * @return Result<UAdmin> - 登录结果，包含管理员对象
     */
    @Override
    public Result loginUser(UAdmin uAdmin) {
        QueryWrapper<UAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hysAname", uAdmin.getName()).eq("hysApass", uAdmin.getPass());
        UAdmin admin = uAdminMapper.selectOne(queryWrapper);
        if (admin != null) {
            return Result.success(admin, "登录成功");
        } else {
            return Result.fail("用户名或密码错误");
        }
    }
}
