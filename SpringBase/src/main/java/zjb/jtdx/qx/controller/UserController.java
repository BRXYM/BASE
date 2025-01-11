package zjb.jtdx.qx.controller;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import zjb.jtdx.qx.domain.User;
import zjb.jtdx.qx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zjb.jtdx.qx.utils.Result;


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

    /**
     * 获取所有用户
     * 请求方式: GET
     * 请求路径: /user/getAllUsers
     * 请求参数: 无
     * 返回结果: Result<List<User>>
     */
    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        System.out.println("收到获取所有用户的请求");
        return userService.getAllUsers();
    }

    /**
     * 增加用户
     * 请求方式: POST
     * 请求路径: /user/addUser
     * 请求参数:
     *   @RequestBody User user - 用户对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        System.out.println("收到增加用户的请求，用户对象: " + user);
        return userService.addUser(user);
    }

    /**
     * 删除用户
     * 请求方式: POST
     * 请求路径: /user/deleteUser
     * 请求参数:
     *   @RequestParam int uid - 用户ID
     * 返回结果: Result<Void>
     */
    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestParam int uid) {
        System.out.println("收到删除用户的请求，用户ID: " + uid);
        return userService.deleteUser(uid);
    }

    /**
     * 修改用户
     * 请求方式: POST
     * 请求路径: /user/updateUser
     * 请求参数:
     *   @RequestBody User user - 用户对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        System.out.println("收到修改用户的请求，用户对象: " + user);
        return userService.updateUser(user);
    }

    /**
     * 用户登录
     * 请求方式: POST
     * 请求路径: /user/login
     * 请求参数:
     *   @RequestBody User user - 用户对象 (包含用户名和密码)
     * 返回结果: Result<User>
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println("收到用户登录的请求，用户对象: " + user);
        return userService.loginUser(user);
    }
}
