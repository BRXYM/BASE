package zjb.jtdx.qx.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import zjb.jtdx.qx.domain.Sela;
import zjb.jtdx.qx.service.SelaService;
import zjb.jtdx.qx.utils.Result;

@RestController
@RequestMapping("/sela")
public class SelaController {

    @Resource
    private SelaService selaService;

    @GetMapping("/getAllSelas")
    public Result getAllSelas() {
        System.out.println("收到获取所有选课的请求");
        return selaService.getAllSelas();
    }

    @PostMapping("/addSela")
    public Result addSela(@RequestBody Sela sela) {
        System.out.println("收到增加选课的请求，选课对象: " + sela);
        return selaService.addSela(sela);
    }

    @PostMapping("/deleteSela")
    public Result deleteSela(@RequestParam int selaId) {
        System.out.println("收到删除选课的请求，选课ID: " + selaId);
        return selaService.deleteSela(selaId);
    }

    @PostMapping("/updateSela")
    public Result updateSela(@RequestBody Sela sela) {
        System.out.println("收到修改选课的请求，选课对象: " + sela);
        return selaService.updateSela(sela);
    }

    @GetMapping("/getSelaById")
    public Result getSelaById(@RequestParam int selaId) {
        System.out.println("收到获取选课的请求，选课ID: " + selaId);
        return selaService.getSelaById(selaId);
    }
}
