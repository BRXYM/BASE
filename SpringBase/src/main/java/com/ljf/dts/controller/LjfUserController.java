package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfUser;
import com.ljf.dts.service.LjfUserService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result login(@RequestParam String phone, @RequestParam String password) {
        return ljfUserService.login(phone, password);
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
    public Result deleteUser(@RequestParam Integer id) {
        return ljfUserService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return ljfUserService.getUserById(id);
    }
}
