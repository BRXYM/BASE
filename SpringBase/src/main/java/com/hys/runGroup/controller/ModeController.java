package com.hys.runGroup.controller;

import com.hys.runGroup.domain.Mode;
import com.hys.runGroup.service.ModeService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 模组控制类
 */

@CrossOrigin
@RestController
@RequestMapping("/mode")
public class ModeController {

    @Resource
    private ModeService modeService;

    /**
     * 获取所有模组
     * 请求方式: GET
     * 请求路径: /mode/getAllModes
     * 请求参数: 无
     * 返回结果: Result<List<Mode>>
     */
    @GetMapping("/getAllModes")
    public Result getAllModes(){
        System.out.println("收到获取所有模组的请求");
        return modeService.getAllModes();
    }

    /**
     * 增加模组
     * 请求方式: POST
     * 请求路径: /mode/addMode
     * 请求参数:
     *   @RequestBody Mode mode - 模组对象
     *   @RequestParam MultipartFile file - 上传的文件
     * 返回结果: Result<Void>
     */
    @PostMapping("/addMode")
    public Result addMode(@RequestBody Mode mode, @RequestParam MultipartFile file) {
        System.out.println("收到增加模组的请求，模组对象: " + mode);
        return modeService.addMode(mode, file);
    }

    /**
     * 删除模组
     * 请求方式: DELETE
     * 请求路径: /mode/deleteMode/{MOid}
     * 请求参数:
     *   @PathVariable int MOid - 模组ID
     * 返回结果: Result<Void>
     */
    @DeleteMapping("/deleteMode/{MOid}")
    public Result deleteMode(@PathVariable int MOid) {
        System.out.println("收到删除模组的请求，模组ID: " + MOid);
        Mode mode = new Mode();
        mode.setMOid(MOid);
        return modeService.deleteMode(mode);
    }

    /**
     * 修改模组
     * 请求方式: PUT
     * 请求路径: /mode/updateMode
     * 请求参数:
     *   @RequestBody Mode mode - 模组对象
     *   @RequestParam(required = false) MultipartFile file - 上传的文件
     * 返回结果: Result<Void>
     */
    @PutMapping("/updateMode")
    public Result updateMode(@RequestBody Mode mode, @RequestParam(required = false) MultipartFile file) {
        System.out.println("收到修改模组的请求，模组对象: " + mode);
        return modeService.updateMode(mode, file);
    }

    /**
     * 根据上传者id获取模组
     * 请求方式: GET
     * 请求路径: /mode/getModesByUid/{Uid}
     * 请求参数:
     *   @PathVariable int Uid - 用户ID
     * 返回结果: Result<List<Mode>>
     */
    @GetMapping("/getModesByUid/{Uid}")
    public Result getModesByUid(@PathVariable int Uid) {
        System.out.println("收到根据上传者ID获取模组的请求，用户ID: " + Uid);
        return modeService.getModesByUid(Uid);
    }

    /**
     * 根据模组id获取模组，并更新收藏数
     * 请求方式: GET
     * 请求路径: /mode/getModeByIdAndUpdateSum/{MOid}
     * 请求参数:
     *   @PathVariable int MOid - 模组ID
     * 返回结果: Result<Mode>
     */
    @GetMapping("/getModeByIdAndUpdateSum/{MOid}")
    public Result getModeByIdAndUpdateSum(@PathVariable int MOid) {
        System.out.println("收到根据模组ID获取模组并更新收藏数的请求，模组ID: " + MOid);
        return modeService.getModeByIdAndUpdateSum(MOid);
    }

    /**
     * 根据类id获取模组
     * 请求方式: GET
     * 请求路径: /mode/getModesByTid/{Tid}
     * 请求参数:
     *   @PathVariable int Tid - 类别ID
     * 返回结果: Result<List<Mode>>
     */
    @GetMapping("/getModesByTid/{Tid}")
    public Result getModesByTid(@PathVariable int Tid) {
        System.out.println("收到根据类ID获取模组的请求，类别ID: " + Tid);
        return modeService.getModesByTid(Tid);
    }
}
