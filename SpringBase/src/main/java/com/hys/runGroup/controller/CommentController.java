package com.hys.runGroup.controller;

import com.hys.runGroup.domain.Comment;
import com.hys.runGroup.service.CommentService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制类
 */

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 获取所有评论
     * 请求方式: POST
     * 请求路径: /comment/getAllComments
     * 请求参数: 无
     * 返回结果: Result<List<Comment>>
     */
    @PostMapping("/getAllComments")
    public Result getAllComments() {
        return commentService.getAllComments();
    }

    /**
     * 根据模组ID获取评论
     * 请求方式: POST
     * 请求路径: /comment/getCommentsByMid
     * 请求参数:
     *   @RequestBody Comment comment - 包含MOid的评论对象
     * 返回结果: Result<List<Comment>>
     */
    @PostMapping("/getCommentsByMid")
    public Result getCommentsByMid(@RequestBody Comment comment) {
        System.out.println(comment);
        return commentService.getCommentsByMid(comment.getMOid());
    }

    /**
     * 添加评论
     * 请求方式: POST
     * 请求路径: /comment/addComment
     * 请求参数:
     *   @RequestBody Comment comment - 评论对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    /**
     * 删除评论
     * 请求方式: POST
     * 请求路径: /comment/deleteComment
     * 请求参数:
     *   @RequestBody Comment comment - 包含CID的评论对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/deleteComment")
    public Result deleteComment(@RequestBody Comment comment) {
        return commentService.deleteComment(comment);
    }

    /**
     * 更新评论
     * 请求方式: POST
     * 请求路径: /comment/updateComment
     * 请求参数:
     *   @RequestBody Comment comment - 评论对象
     * 返回结果: Result<Void>
     */
    @PostMapping("/updateComment")
    public Result updateComment(@RequestBody Comment comment) {
        return commentService.updateComment(comment);
    }
}
