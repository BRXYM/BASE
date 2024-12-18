package com.hys.runGroup.controller;

import com.hys.runGroup.domain.Comment;
import com.hys.runGroup.service.CommentService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/getAllComments")
    public Result getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping("/getCommentsByMid")
    public Result getCommentsByMid(@RequestBody Comment comment) {
        System.out.println(comment);
        return commentService.getCommentsByMid(20000);
    }

    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @PostMapping("/deleteComment")
    public Result deleteComment(@RequestBody Comment comment) {
        return commentService.deleteComment(comment);
    }

    @PostMapping("/updateComment")
    public Result updateComment(@RequestBody Comment comment) {
        return commentService.updateComment(comment);
    }
}
