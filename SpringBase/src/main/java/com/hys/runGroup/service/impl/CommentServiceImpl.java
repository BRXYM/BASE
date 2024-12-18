package com.hys.runGroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hys.runGroup.domain.Comment;
import com.hys.runGroup.mapper.CommentMapper;
import com.hys.runGroup.service.CommentService;
import com.hys.runGroup.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result getAllComments() {
        List<Comment> comments = commentMapper.selectList(null);
        return Result.success(comments,"获取成功");
    }

    @Override
    public Result getCommentsByMid(Integer id) {
//        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("MOid", id);
        List<Comment> comments = commentMapper.selectByMOid(id);
        System.out.println(comments);
        return Result.success(comments,"获取成功");
    }

    @Override
    public Result addComment(Comment comment) {
        boolean save = commentMapper.insert(comment) > 0;
        if (save) {
            return Result.success();
        } else {
            return Result.fail("添加评论失败");
        }
    }

    @Override
    public Result deleteComment(Comment comment) {
        boolean remove = commentMapper.deleteById(comment.getCid()) > 0;
        if (remove) {
            return Result.success();
        } else {
            return Result.fail("删除评论失败");
        }
    }

    @Override
    public Result updateComment(Comment comment) {
        boolean update = commentMapper.updateById(comment) > 0;
        if (update) {
            return Result.success();
        } else {
            return Result.fail("更新评论失败");
        }
    }
}
