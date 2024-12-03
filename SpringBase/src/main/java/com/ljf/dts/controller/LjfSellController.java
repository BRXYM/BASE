package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfSell;
import com.ljf.dts.service.LjfSellService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/sell")
public class LjfSellController {

    @Autowired
    private LjfSellService ljfSellService;

    @GetMapping
    public Result getSells() {
        // Assuming there's a method to get all sells
        return ljfSellService.getAllSells();
    }

    @PostMapping("/add")
    public Result addSell(@RequestBody LjfSell sell) {
        return ljfSellService.addSell(sell);
    }

    @PostMapping("/update")
    public Result updateSell(@RequestBody LjfSell sell) {
        return ljfSellService.updateSell(sell);
    }

    @PostMapping("/delete")
    public Result deleteSell(@RequestBody LjfSell sell) {
        return ljfSellService.deleteSell(sell.getSell_id());
    }
}
