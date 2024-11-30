package com.ljf.dts.service;

import com.ljf.dts.domain.LjfAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.dts.utils.Result;

public interface LjfAdminService extends IService<LjfAdmin> {
    Result getAllAdmins();
    Result addAdmin(LjfAdmin admin);
    Result updateAdmin(LjfAdmin admin);
    Result deleteAdmin(Integer id);
    Result getAdminById(Integer id);
}
