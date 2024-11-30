package com.ljf.dts.service;

import com.ljf.dts.domain.LjfGood;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.dts.utils.Result;

public interface LjfGoodService extends IService<LjfGood> {
    Result getAllGoods();
    Result addGood(LjfGood good);
    Result updateGood(LjfGood good);
    Result deleteGood(Integer id);
    Result getGoodById(Integer id);
}
