package com.ljf.dts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljf.dts.domain.LjfAdmin;
import com.ljf.dts.service.LjfAdminService;
import com.ljf.dts.mapper.LjfAdminMapper;
import com.ljf.dts.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjfAdminServiceImpl extends ServiceImpl<LjfAdminMapper, LjfAdmin>
    implements LjfAdminService {

    @Override
    public Result getAllAdmins() {
        List<LjfAdmin> admins = list();
        return new Result(200, "查询成功", admins, "查询成功");
    }

    @Override
    public Result addAdmin(LjfAdmin admin) {
        boolean save = save(admin);
        if (save) {
            return new Result(200, "添加成功", admin, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result updateAdmin(LjfAdmin admin) {
        boolean update = updateById(admin);
        if (update) {
            return new Result(200, "更新成功", admin, "更新成功");
        } else {
            return new Result(400, "更新失败", null, "更新失败");
        }
    }

    @Override
    public Result deleteAdmin(Integer id) {
        boolean delete = removeById(id);
        if (delete) {
            return new Result(200, "删除成功", null, "删除成功");
        } else {
            return new Result(400, "删除失败", null, "删除失败");
        }
    }

    @Override
    public Result getAdminById(Integer id) {
        LjfAdmin admin = getById(id);
        if (admin != null) {
            return new Result(200, "查询成功", admin, "查询成功");
        } else {
            return new Result(400, "查询失败", null, "管理员不存在");
        }
    }
}
