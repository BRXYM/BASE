package com.hys.runGroup.controller;

import com.hys.runGroup.service.ModeService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模组控制类
 */

@CrossOrigin
@RestController
@RequestMapping("/mode")
public class ModeController {

    @Resource
    private ModeService modeService;

    @GetMapping("/getAllModes")
    public Result getAllModes(){
        return modeService.getAllModes();
    }
}
