package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfUser;
import com.ljf.dts.service.LjfUserService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LjfUserController {

    @Autowired
    private LjfUserService ljfUserService;

    @GetMapping
    public Result getUsers() {
        return ljfUserService.getUser();
    }

    @PostMapping("/login")
    public Result login(@RequestBody LjfUser user) {
        System.out.println(user);
        return ljfUserService.login(user.getUser_phone(), user.getUser_psword());
    }

    @PostMapping("/add")
    public Result addUser(@RequestBody LjfUser user) {
        return ljfUserService.addUser(user);
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody LjfUser user) {
        return ljfUserService.updateUser(user);
    }

    @PostMapping("/delete")
    public Result deleteUser(@RequestBody LjfUser user) {
        return ljfUserService.deleteUser(user.getUser_id());
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return ljfUserService.getUserById(id);
    }
}
