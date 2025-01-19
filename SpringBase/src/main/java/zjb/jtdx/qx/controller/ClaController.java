package zjb.jtdx.qx.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import zjb.jtdx.qx.domain.Cla;
import zjb.jtdx.qx.service.ClaService;
import zjb.jtdx.qx.utils.Result;

@RestController
@RequestMapping("/cla")
public class ClaController {

    @Resource
    private ClaService claService;

    @GetMapping("/getAllClas")
    public Result getAllClas() {
        System.out.println("收到获取所有课程的请求");
        return claService.getAllClas();
    }

    @PostMapping("/addCla")
    public Result addCla(@RequestBody Cla cla) {
        System.out.println("收到增加课程的请求，课程对象: " + cla);
        return claService.addCla(cla);
    }

    @PostMapping("/deleteCla")
    public Result deleteCla(@RequestParam int claId) {
        System.out.println("收到删除课程的请求，课程ID: " + claId);
        return claService.deleteCla(claId);
    }

    @PostMapping("/updateCla")
    public Result updateCla(@RequestBody Cla cla) {
        System.out.println("收到修改课程的请求，课程对象: " + cla);
        return claService.updateCla(cla);
    }

    @GetMapping("/getClaById")
    public Result getClaById(@RequestParam int claId) {
        System.out.println("收到获取课程的请求，课程ID: " + claId);
        return claService.getClaById(claId);
    }

    @GetMapping("/getClasByTeacherId")
    public Result getClasByTeacherId(@RequestParam int teacherId) {
        System.out.println("收到根据教师ID获取课程的请求，教师ID: " + teacherId);
        return claService.getClasByTeacherId(teacherId);
    }
}
