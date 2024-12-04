package com.ljf.dts.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljf.dts.domain.LjfMess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 26371
* @description 针对表【ljf_mess(留言表)】的数据库操作Mapper
* @createDate 2024-11-30 22:14:03
* @Entity com.ljf.dts.domain.LjfMess
*/
public interface LjfMessMapper extends BaseMapper<LjfMess> {
    default List<LjfMess> getByOrderId(Integer orderId){
        QueryWrapper<LjfMess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ljf_mess_order_id", orderId);
        return selectList(queryWrapper);
    }

}




