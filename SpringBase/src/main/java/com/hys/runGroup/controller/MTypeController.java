package com.hys.runGroup.controller;

import com.hys.runGroup.service.MTypeService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 分类控制类
 */

@CrossOrigin
@RestController
@RequestMapping("/mtype")
public class MTypeController {

    @Resource
    private MTypeService mTypeService;

    /**
     * 获取所有分类信息
     * 请求方式: GET
     * 请求路径: /mtype/getAllMTypes
     * 请求参数: 无
     * 返回结果: Result<List<MType>>
     */
    @GetMapping("/getAllMTypes")
    public Result getAllMTypes() {
        System.out.println("收到获取所有分类信息的请求");
        return mTypeService.getAllMTypes();
    }

    /**
     * 根据id获取分类名
     * 请求方式: GET
     * 请求路径: /mtype/getMTypeById/{id}
     * 请求参数:
     *   @PathVariable int id - 分类ID
     * 返回结果: Result<MType>
     */
    @GetMapping("/getMTypeById/{id}")
    public Result getMTypeById(@PathVariable int id) {
        System.out.println("收到根据ID获取分类名的请求，分类ID: " + id);
        return mTypeService.getMTypeById(id);
    }
}
