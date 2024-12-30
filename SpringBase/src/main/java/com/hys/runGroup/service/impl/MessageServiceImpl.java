package com.hys.runGroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hys.runGroup.domain.Message;
import com.hys.runGroup.mapper.MessageMapper;
import com.hys.runGroup.service.MessageService;
import com.hys.runGroup.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 留言表服务层实现类
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 添加留言
     * @param message - 留言对象
     * @return Result - 添加留言的结果
     */
    @Override
    @Transactional
    public Result addMessage(Message message) {
        int result = messageMapper.insert(message);
        if (result > 0) {
            return Result.success("留言添加成功");
        } else {
            return Result.fail("留言添加失败");
        }
    }

    /**
     * 删除留言
     * @param message - 包含MEid的留言对象
     * @return Result - 删除留言的结果
     */
    @Override
    @Transactional
    public Result deleteMessage(Message message) {
        int result = messageMapper.deleteById(message.getMEid());
        if (result > 0) {
            return Result.success("留言删除成功");
        } else {
            return Result.fail("留言删除失败");
        }
    }

    /**
     * 获取所有留言
     * @return Result<List<Message>> - 包含所有留言的Result对象
     */
    @Override
    public Result getAllMessages() {
        List<Message> messages = messageMapper.selectList(new QueryWrapper<>());
        return Result.success(messages, "获取所有留言成功");
    }

    /**
     * 根据用户Uid获取留言信息
     * @param uid - 用户ID
     * @return Result<List<Message>> - 包含指定用户ID的留言列表的Result对象
     */
    @Override
    public Result getMessagesByUid(Integer uid) {
        List<Message> list = messageMapper.selectList(new QueryWrapper<Message>().eq("hysMEidU", uid));
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 根据被评论用户Utoid获取评论信息
     * @param toid - 被评论用户ID
     * @return Result<List<Message>> - 包含指定被评论用户ID的留言列表的Result对象
     */
    @Override
    public Result getMessagesByToUid(Integer toid) {
        List<Message> messages = messageMapper.selectList(new QueryWrapper<Message>().eq("hysMEtoid", toid));
        return Result.success(messages, "获取当前用户新留言成功");
    }

    /**
     * 更新留言信息
     * @param message - 留言对象
     * @return Result - 更新留言的结果
     */
    @Override
    public Result updateMessage(Message message) {
        int result = messageMapper.updateById(message);
        if (result > 0) {
            return Result.success("留言更新成功");
        } else {
            return Result.fail("留言更新失败");
        }
    }
}
