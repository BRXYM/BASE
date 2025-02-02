package zjb.jtdx.qx.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import zjb.jtdx.qx.domain.Teach;
import zjb.jtdx.qx.service.TeachService;
import zjb.jtdx.qx.utils.Result;

@RestController
@RequestMapping("/teach")
public class TeachController {

    @Resource
    private TeachService teachService;

    @GetMapping("/getAllTeaches")
    public Result getAllTeaches() {
        System.out.println("收到获取所有教练的请求");
        return teachService.getAllTeaches();
    }

    @PostMapping("/addTeach")
    public Result addTeach(@RequestBody Teach teach) {
        System.out.println("收到增加教练的请求，教练对象: " + teach);
        return teachService.addTeach(teach);
    }

    @PostMapping("/deleteTeach")
    public Result deleteTeach(@RequestParam int teachId) {
        System.out.println("收到删除教练的请求，教练ID: " + teachId);
        return teachService.deleteTeach(teachId);
    }

    @PostMapping("/updateTeach")
    public Result updateTeach(@RequestBody Teach teach) {
        System.out.println("收到修改教练的请求，教练对象: " + teach);
        return teachService.updateTeach(teach);
    }

    @GetMapping("/getTeachById")
    public Result getTeachById(@RequestParam int teachId) {
        System.out.println("收到获取教练的请求，教练ID: " + teachId);
        return teachService.getTeachById(teachId);
    }
    @PostMapping("/login")
    public Result login(@RequestBody Teach teach) {
        System.out.println("收到登录请求," + teach);
        return teachService.login(teach.getTeachPhone(), teach.getTeachPass());
    }
}
