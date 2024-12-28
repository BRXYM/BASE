package com.hys.runGroup.controller;

import com.hys.runGroup.domain.UAdmin;
import com.hys.runGroup.service.UAdminService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员控制类
 */

@CrossOrigin
@RestController
@RequestMapping("/uadmin")
public class UAdminController {
    @Resource
    private UAdminService uAdminService;

    /**
     * 管理员登录
     * 请求方式: POST
     * 请求路径: /uadmin/login
     * 请求参数:
     *   @RequestBody UAdmin uAdmin - 管理员对象 (包含用户名和密码)
     * 返回结果: Result<UAdmin>
     */
    @PostMapping("/login")
    public Result login(@RequestBody UAdmin uAdmin) {
        System.out.println("收到管理员登录的请求，管理员对象: " + uAdmin);
        return uAdminService.loginUser(uAdmin);
    }
}
