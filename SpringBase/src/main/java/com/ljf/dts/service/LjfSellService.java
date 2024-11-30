package com.ljf.dts.service;

import com.ljf.dts.domain.LjfSell;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.dts.utils.Result;

import java.util.List;

public interface LjfSellService extends IService<LjfSell> {
    Result getAllSells();
    Result addSell(LjfSell sell);
    Result updateSell(LjfSell sell);
    Result deleteSell(Integer id);
    Result getSellById(Integer id);
}
