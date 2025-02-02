package zjb.jtdx.qx.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import zjb.jtdx.qx.domain.Good;
import zjb.jtdx.qx.service.GoodService;
import zjb.jtdx.qx.utils.Result;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Resource
    private GoodService goodService;

    @GetMapping("/getAllGoods")
    public Result getAllGoods() {
        System.out.println("收到获取所有商品的请求");
        return goodService.getAllGoods();
    }

    @PostMapping("/addGood")
    public Result addGood(@RequestBody Good good) {
        System.out.println("收到增加商品的请求，商品对象: " + good);
        return goodService.addGood(good);
    }

    @GetMapping("/deleteGood")
    public Result deleteGood(@RequestParam int goodId) {
        System.out.println("收到删除商品的请求，商品ID: " + goodId);
        return goodService.deleteGood(goodId);
    }

    @PostMapping("/updateGood")
    public Result updateGood(@RequestBody Good good) {
        System.out.println("收到修改商品的请求，商品对象: " + good);
        return goodService.updateGood(good);
    }

    @GetMapping("/getGoodById")
    public Result getGoodById(@RequestParam int goodId) {
        System.out.println("收到获取商品的请求，商品ID: " + goodId);
        return goodService.getGoodById(goodId);
    }
}
