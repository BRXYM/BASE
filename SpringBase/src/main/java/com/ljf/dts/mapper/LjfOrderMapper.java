package com.ljf.dts.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljf.dts.domain.LjfOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 26371
* @description 针对表【ljf_order(订单表)】的数据库操作Mapper
* @createDate 2024-11-30 22:14:05
* @Entity com.ljf.dts.domain.LjfOrder
*/
public interface LjfOrderMapper extends BaseMapper<LjfOrder> {

    default List<LjfOrder> selectByUserId(Integer userId) {
        QueryWrapper<LjfOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ljf_order_user_id", userId);
        return selectList(queryWrapper);
    }
}




