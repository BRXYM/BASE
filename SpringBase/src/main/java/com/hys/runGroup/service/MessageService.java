package com.hys.runGroup.service;


import com.hys.runGroup.domain.Message;
import com.hys.runGroup.utils.Result;

/**
 * 留言表服务层接口
 */
public interface MessageService {

//    新增留言
    Result addMessage(Message message);
//    删除留言
    Result deleteMessage(Message message);
//    获取所有留言
    Result getAllMessages();
//    获取当前用户历史留言
    Result getMessagesByUid(Message message);
//    获取当前用户新留言
    Result getMessagesByToUid(Message message);
//    更新留言
    Result updateMessage(Message message);
}
