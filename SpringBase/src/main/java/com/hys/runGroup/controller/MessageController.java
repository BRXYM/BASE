package com.hys.runGroup.controller;

import com.hys.runGroup.domain.Message;
import com.hys.runGroup.service.MessageService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 留言表控制类
 */

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 根据用户Uid获取留言信息
     * 请求方式: GET
     * 请求路径: /message/byUid
     * 请求参数:
     *   @RequestParam int uid - 用户ID
     * 返回结果: Result<List<Message>>
     */
    @GetMapping("/byUid")
    public Result getMessagesByUid(@RequestParam int uid) {
        System.out.println("收到根据用户ID获取留言信息的请求，用户ID: " + uid);
        return messageService.getMessagesByUid(uid);
    }

    /**
     * 根据被评论用户Utoid获取评论信息
     * 请求方式: GET
     * 请求路径: /message/byToUid
     * 请求参数:
     *   @RequestParam int utoid - 被评论用户ID
     * 返回结果: Result<List<Message>>
     */
    @GetMapping("/byToUid")
    public Result getMessagesByToUid(@RequestParam int utoid) {
        System.out.println("收到根据被评论用户ID获取评论信息的请求，被评论用户ID: " + utoid);
        return messageService.getMessagesByToUid(utoid);
    }

    /**
     * 添加留言信息
     * 请求方式: POST
     * 请求路径: /message/add
     * 请求参数:
     *   @RequestBody Message message - 留言对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/add")
    public Result addMessage(@RequestBody Message message) {
        System.out.println("收到添加留言信息的请求，留言对象: " + message);
        return messageService.addMessage(message);
    }

    /**
     * 删除留言信息
     * 请求方式: POST
     * 请求路径: /message/delete
     * 请求参数:
     *   @RequestParam int MEid - 留言ID
     * 返回结果: Result<Void>
     */
    @PostMapping("/delete")
    public Result deleteMessage(@RequestParam int MEid) {
        System.out.println("收到删除留言信息的请求，留言ID: " + MEid);
        Message message = new Message();
        message.setMEid(MEid);
        return messageService.deleteMessage(message);
    }

    /**
     * 获取所有留言
     * 请求方式: GET
     * 请求路径: /message/all
     * 请求参数: 无
     * 返回结果: Result<List<Message>>
     */
    @GetMapping("/all")
    public Result getAllMessages() {
        System.out.println("收到获取所有留言的请求");
        return messageService.getAllMessages();
    }

    /**
     * 更新留言信息
     * 请求方式: POST
     * 请求路径: /message/update
     * 请求参数:
     *   @RequestBody Message message - 留言对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/update")
    public Result updateMessage(@RequestBody Message message) {
        System.out.println("收到更新留言信息的请求，留言对象: " + message);
        return messageService.updateMessage(message);
    }
}
