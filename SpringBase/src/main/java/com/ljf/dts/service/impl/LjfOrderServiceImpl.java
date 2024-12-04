package com.ljf.dts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljf.dts.domain.LjfOrder;
import com.ljf.dts.service.LjfOrderService;
import com.ljf.dts.mapper.LjfOrderMapper;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjfOrderServiceImpl extends ServiceImpl<LjfOrderMapper, LjfOrder>
    implements LjfOrderService {
    @Autowired
    private LjfOrderMapper ljfOrderMapper;
    @Override
    public Result getAllOrders() {
        List<LjfOrder> orders = list();
        return new Result(200, "查询成功", orders, "查询成功");
    }

    @Override
    public Result addOrder(LjfOrder order) {
        boolean save = save(order);
        if (save) {
            return new Result(200, "添加成功", order, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result deleteOrder(Integer id) {
        boolean delete = removeById(id);
        if (delete) {
            return new Result(200, "删除成功", null, "删除成功");
        } else {
            return new Result(400, "删除失败", null, "删除失败");
        }
    }

    @Override
    public Result updateOrder(LjfOrder order) {
        boolean update = updateById(order);
        if (update) {
            return new Result(200, "添加成功", order, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result getOrderById(Integer id) {
        List<LjfOrder> order = ljfOrderMapper.selectByUserId(id);
        if (order != null) {
            return new Result(200, "查询成功", order, "查询成功");
        } else {
            return new Result(400, "查询失败", null, "订单不存在");
        }
    }
}
