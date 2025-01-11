package zjb.jtdx.qx.controller;

import com.test.example.utils.Result;
import org.springframework.http.ResponseEntity;
import zjb.jtdx.qx.domain.User;
import zjb.jtdx.qx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUser")
    public Result getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}