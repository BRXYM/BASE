package zjb.jtdx.qx.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import zjb.jtdx.qx.domain.Ord;
import zjb.jtdx.qx.service.OrdService;
import zjb.jtdx.qx.utils.Result;

@RestController
@RequestMapping("/ord")
public class OrdController {

    @Resource
    private OrdService ordService;

    @GetMapping("/getAllOrds")
    public Result getAllOrds() {
        System.out.println("收到获取所有订单的请求");
        return ordService.getAllOrds();
    }

    @PostMapping("/addOrd")
    public Result addOrd(@RequestBody Ord ord) {
        System.out.println("收到增加订单的请求，订单对象: " + ord);
        return ordService.addOrd(ord);
    }

    @PostMapping("/deleteOrd")
    public Result deleteOrd(@RequestParam int ordId) {
        System.out.println("收到删除订单的请求，订单ID: " + ordId);
        return ordService.deleteOrd(ordId);
    }

    @PostMapping("/updateOrd")
    public Result updateOrd(@RequestBody Ord ord) {
        System.out.println("收到修改订单的请求，订单对象: " + ord);
        return ordService.updateOrd(ord);
    }

    @GetMapping("/getOrdById")
    public Result getOrdById(@RequestParam int ordId) {
        System.out.println("收到获取订单的请求，订单ID: " + ordId);
        return ordService.getOrdById(ordId);
    }
}
