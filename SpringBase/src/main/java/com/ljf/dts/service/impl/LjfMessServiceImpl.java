package com.ljf.dts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljf.dts.domain.LjfMess;
import com.ljf.dts.service.LjfMessService;
import com.ljf.dts.mapper.LjfMessMapper;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjfMessServiceImpl extends ServiceImpl<LjfMessMapper, LjfMess>
    implements LjfMessService {

    @Autowired
    private LjfMessMapper messMapper;

    @Override
    public Result getAllMessages() {
        List<LjfMess> messages = list();
        return new Result(200, "查询成功", messages, "查询成功");
    }

    @Override
    public Result addMessage(LjfMess message) {
        boolean save = save(message);
        if (save) {
            return new Result(200, "添加成功", message, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result updateMessage(LjfMess message) {
        boolean update = updateById(message);
        if (update) {
            return new Result(200, "更新成功", message, "更新成功");
        } else {
            return new Result(400, "更新失败", null, "更新失败");
        }
    }

    @Override
    public Result deleteMessage(Integer id) {
        boolean delete = removeById(id);
        if (delete) {
            return new Result(200, "删除成功", null, "删除成功");
        } else {
            return new Result(400, "删除失败", null, "删除失败");
        }
    }

    @Override
    public Result getMessageByOrderId(Integer orderid) {
        List<LjfMess> list = messMapper.getByOrderId(orderid);
        if (list != null) {
            return new Result(200, "查询成功", list, "查询成功");
        } else {
            return new Result(400, "查询失败", null, "留言不存在");
        }
    }
}
