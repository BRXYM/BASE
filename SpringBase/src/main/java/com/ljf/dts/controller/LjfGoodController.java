package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfGood;
import com.ljf.dts.service.LjfGoodService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/good")
public class LjfGoodController {

    @Autowired
    private LjfGoodService ljfGoodService;

    @GetMapping
    public Result getGoods() {
        // Assuming there's a method to get all goods
        return ljfGoodService.getAllGoods();
    }

    @PostMapping("/add")
    public Result addGood(@RequestBody LjfGood good) {
        return ljfGoodService.addGood(good);
    }

    @PostMapping("/update")
    public Result updateGood(@RequestBody LjfGood good) {
        return ljfGoodService.updateGood(good);
    }

    @PostMapping("/delete")
    public Result deleteGood(@RequestBody LjfGood good) {
        return ljfGoodService.deleteGood(good.getGood_id());
    }

    @PostMapping("/getById")
    public Result getGoodById(@RequestBody LjfGood good) {
        return ljfGoodService.getGoodById(good.getGood_id());
    }
}
