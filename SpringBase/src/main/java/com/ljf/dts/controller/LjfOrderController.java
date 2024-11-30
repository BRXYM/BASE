package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfOrder;
import com.ljf.dts.service.LjfOrderService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class LjfOrderController {

    @Autowired
    private LjfOrderService ljfOrderService;

    @GetMapping
    public Result getOrders() {
        // Assuming there's a method to get all orders
        return ljfOrderService.getAllOrders();
    }

    @PostMapping("/add")
    public Result addOrder(@RequestBody LjfOrder order) {
        return ljfOrderService.addOrder(order);
    }

    @PostMapping("/update")
    public Result updateOrder(@RequestBody LjfOrder order) {
        return ljfOrderService.updateOrder(order);
    }

    @PostMapping("/delete")
    public Result deleteOrder(@RequestParam Integer id) {
        return ljfOrderService.deleteOrder(id);
    }
}
