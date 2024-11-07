package com.hys.runGroup.controller;


import com.hys.runGroup.domain.User;
import com.hys.runGroup.service.UserService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制类
 * 获取所有用户
 * 增加用户
 * 删除用户
 * 修改用户
 * 登录
 */

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
//        System.out.println(user);
        return userService.loginUser(user);
    }
}
