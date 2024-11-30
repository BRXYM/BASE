package com.ljf.dts.service;

import com.ljf.dts.domain.LjfOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.dts.utils.Result;

import java.util.List;

public interface LjfOrderService extends IService<LjfOrder> {
    Result getAllOrders();
    Result addOrder(LjfOrder order);
    Result deleteOrder(Integer id);
    Result updateOrder(LjfOrder order);
    Result getOrderById(Integer id);
}
