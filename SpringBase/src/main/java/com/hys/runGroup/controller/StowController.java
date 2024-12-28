package com.hys.runGroup.controller;

import com.hys.runGroup.domain.Stow;
import com.hys.runGroup.service.StowService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏表控制类
 */

@CrossOrigin
@RestController
@RequestMapping("/stow")
public class StowController {

    @Resource
    private StowService stowService;

    /**
     * 获取所有收藏信息
     * 请求方式: GET
     * 请求路径: /stow/getAllStows
     * 请求参数: 无
     * 返回结果: Result<List<Stow>>
     */
    @GetMapping("/getAllStows")
    public Result getAllStows() {
        System.out.println("收到获取所有收藏信息的请求");
        return stowService.getAllStows();
    }

    /**
     * 根据id获取收藏
     * 请求方式: GET
     * 请求路径: /stow/getStowById/{id}
     * 请求参数:
     *   @PathVariable int id - 收藏ID
     * 返回结果: Result<Stow>
     */
    @GetMapping("/getStowById/{id}")
    public Result getStowById(@PathVariable int id) {
        System.out.println("收到根据ID获取收藏的请求，收藏ID: " + id);
        return stowService.getStowById(id);
    }

    /**
     * 根据用户id获取收藏信息
     * 请求方式: GET
     * 请求路径: /stow/getStowsByUid/{Uid}
     * 请求参数:
     *   @PathVariable int Uid - 用户ID
     * 返回结果: Result<List<Stow>>
     */
    @GetMapping("/getStowsByUid/{Uid}")
    public Result getStowsByUid(@PathVariable int Uid) {
        System.out.println("收到根据用户ID获取收藏信息的请求，用户ID: " + Uid);
        return stowService.getStowsByUid(Uid);
    }

    /**
     * 添加收藏
     * 请求方式: POST
     * 请求路径: /stow/addStow
     * 请求参数:
     *   @RequestBody Stow stow - 收藏对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/addStow")
    public Result addStow(@RequestBody Stow stow) {
        System.out.println("收到添加收藏的请求，收藏对象: " + stow);
        return stowService.addStow(stow);
    }

    /**
     * 删除收藏
     * 请求方式: DELETE
     * 请求路径: /stow/deleteStow/{id}
     * 请求参数:
     *   @PathVariable int id - 收藏ID
     * 返回结果: Result<Void>
     */
    @DeleteMapping("/deleteStow/{id}")
    public Result deleteStow(@PathVariable int id) {
        System.out.println("收到删除收藏的请求，收藏ID: " + id);
        Stow stow = new Stow();
        stow.setSid(id);
        return stowService.deleteStow(stow);
    }

    /**
     * 修改收藏
     * 请求方式: PUT
     * 请求路径: /stow/updateStow
     * 请求参数:
     *   @RequestBody Stow stow - 收藏对象
     * 返回结果: Result<Void>
     */
    @PutMapping("/updateStow")
    public Result updateStow(@RequestBody Stow stow) {
        System.out.println("收到更新收藏的请求，收藏对象: " + stow);
        return stowService.updateStow(stow);
    }
}
