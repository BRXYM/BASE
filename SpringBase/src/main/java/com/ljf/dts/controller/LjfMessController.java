package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfMess;
import com.ljf.dts.service.LjfMessService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/mess")
public class LjfMessController {

    @Autowired
    private LjfMessService ljfMessService;

    @GetMapping
    public Result getMessages() {
        return ljfMessService.getAllMessages();
    }

    @PostMapping("/add")
    public Result addMessage(@RequestBody LjfMess message) {
        return ljfMessService.addMessage(message);
    }

    @PostMapping("/update")
    public Result updateMessage(@RequestBody LjfMess message) {
        return ljfMessService.updateMessage(message);
    }

    @PostMapping("/delete")
    public Result deleteMessage(@RequestBody LjfMess message) {
        return ljfMessService.deleteMessage(message.getMess_id());
    }

    @PostMapping("/byOrderId")
    public Result getMessageByOrderId(@RequestBody LjfMess message) {
        System.out.println(message);
        return ljfMessService.getMessageByOrderId(message.getOrder_id());
    }
}
