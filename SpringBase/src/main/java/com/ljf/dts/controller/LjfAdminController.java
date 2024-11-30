package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfAdmin;
import com.ljf.dts.service.LjfAdminService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class LjfAdminController {

    @Autowired
    private LjfAdminService ljfAdminService;

    @GetMapping
    public Result getAdmins() {
        // Assuming there's a method to get all admins
        return ljfAdminService.getAllAdmins();
    }

    @PostMapping("/add")
    public Result addAdmin(@RequestBody LjfAdmin admin) {
        return ljfAdminService.addAdmin(admin);
    }

    @PostMapping("/update")
    public Result updateAdmin(@RequestBody LjfAdmin admin) {
        return ljfAdminService.updateAdmin(admin);
    }

    @PostMapping("/delete")
    public Result deleteAdmin(@RequestBody LjfAdmin admin) {
        return ljfAdminService.deleteAdmin(admin.getAdm_id());
    }
}
