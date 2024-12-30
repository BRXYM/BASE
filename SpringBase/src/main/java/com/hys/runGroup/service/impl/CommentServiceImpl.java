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

    /**
     * 获取所有评论
     * @return Result<List<Comment>> - 包含所有评论的Result对象
     */
    @Override
    public Result getAllComments() {
        List<Comment> comments = commentMapper.selectList(null);
        return Result.success(comments, "获取成功");
    }

    /**
     * 根据模组ID获取评论
     * @param id - 模组ID
     * @return Result<List<Comment>> - 包含指定模组ID的评论列表的Result对象
     */
    @Override
    public Result getCommentsByMid(Integer id) {
        List<Comment> list = commentMapper.selectList(new QueryWrapper<Comment>().eq("hysCidMO",id));
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 添加评论
     * @param comment - 评论对象
     * @return Result - 添加评论的结果
     */
    @Override
    public Result addComment(Comment comment) {
        boolean save = commentMapper.insert(comment) > 0;
        if (save) {
            return Result.success("添加评论成功");
        } else {
            return Result.fail("添加评论失败");
        }
    }

    /**
     * 删除评论
     * @param comment - 包含CID的评论对象
     * @return Result - 删除评论的结果
     */
    @Override
    public Result deleteComment(Comment comment) {
        boolean remove = commentMapper.deleteById(comment.getCid()) > 0;
        if (remove) {
            return Result.success("删除评论成功");
        } else {
            return Result.fail("删除评论失败");
        }
    }

    /**
     * 更新评论
     * @param comment - 评论对象
     * @return Result - 更新评论的结果
     */
    @Override
    public Result updateComment(Comment comment) {
        boolean update = commentMapper.updateById(comment) > 0;
        if (update) {
            return Result.success("更新评论成功");
        } else {
            return Result.fail("更新评论失败");
        }
    }
}
