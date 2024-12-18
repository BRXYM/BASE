package com.hys.runGroup.service;

import com.hys.runGroup.domain.Comment;
import com.hys.runGroup.utils.Result;

/**
 * 评论表服务层接口
 */
public interface CommentService {
    Result getAllComments();
    Result getCommentsByMid(Integer id);
    Result addComment(Comment comment);
    Result deleteComment(Comment comment);
    Result updateComment(Comment comment);
}
