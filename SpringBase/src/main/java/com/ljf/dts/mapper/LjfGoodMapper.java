package com.ljf.dts.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljf.dts.domain.LjfGood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljf.dts.domain.LjfOrder;

import java.util.List;

/**
* @author 26371
* @description 针对表【ljf_good(商品列表)】的数据库操作Mapper
* @createDate 2024-11-30 22:14:01
* @Entity com.ljf.dts.domain.LjfGood
*/
public interface LjfGoodMapper extends BaseMapper<LjfGood> {
    default LjfGood getByGoodId(Integer goodId) {
        QueryWrapper<LjfGood> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ljf_good_id", goodId);
        return selectOne(queryWrapper);
    }
}




