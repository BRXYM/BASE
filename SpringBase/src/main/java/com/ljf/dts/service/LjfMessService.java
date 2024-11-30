package com.ljf.dts.service;

import com.ljf.dts.domain.LjfMess;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.dts.utils.Result;

public interface LjfMessService extends IService<LjfMess> {
    Result getAllMessages();
    Result addMessage(LjfMess message);
    Result updateMessage(LjfMess message);
    Result deleteMessage(Integer id);
    Result getMessageByOrderId(Integer orderid);
}
