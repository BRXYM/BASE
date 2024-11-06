package com.hys.runGroup.controller;


import com.hys.runGroup.domain.User;
import com.hys.runGroup.service.UserService;
import com.hys.runGroup.utils.Result;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public Result addUser(User user) {
        return userService.addUser(user);
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(User user) {
        return userService.deleteUser(user);
    }

    @PostMapping("/updateUser")
    public Result updateUser(User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/login")
    public Result login(User user) {
        return userService.loginUser(user);
    }
}
