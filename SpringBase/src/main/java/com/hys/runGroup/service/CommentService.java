package com.hys.runGroup.service;

import com.hys.runGroup.domain.Comment;
import com.hys.runGroup.utils.Result;


/**
 * 评论表服务层接口
 */
public interface CommentService {

//    获取所有评论
    Result getAllComments();
//    根据Mid获取评论
    Result getCommentsByMid(Comment comment);
//    增加评论
    Result addComment(Comment comment);
//    删除评论
    Result deleteComment(Comment comment);
//    修改评论
    Result updateComment(Comment comment);
}
