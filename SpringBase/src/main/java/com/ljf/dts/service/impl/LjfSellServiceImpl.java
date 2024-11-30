package com.ljf.dts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljf.dts.domain.LjfSell;
import com.ljf.dts.service.LjfSellService;
import com.ljf.dts.mapper.LjfSellMapper;
import com.ljf.dts.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjfSellServiceImpl extends ServiceImpl<LjfSellMapper, LjfSell>
    implements LjfSellService {

    @Override
    public Result getAllSells() {
        List<LjfSell> sells = list();
        return new Result(200, "查询成功", sells, "查询成功");
    }

    @Override
    public Result addSell(LjfSell sell) {
        boolean save = save(sell);
        if (save) {
            return new Result(200, "添加成功", sell, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result updateSell(LjfSell sell) {
        boolean update = updateById(sell);
        if (update) {
            return new Result(200, "更新成功", sell, "更新成功");
        } else {
            return new Result(400, "更新失败", null, "更新失败");
        }
    }

    @Override
    public Result deleteSell(Integer id) {
        boolean delete = removeById(id);
        if (delete) {
            return new Result(200, "删除成功", null, "删除成功");
        } else {
            return new Result(400, "删除失败", null, "删除失败");
        }
    }

    @Override
    public Result getSellById(Integer id) {
        LjfSell sell = getById(id);
        if (sell != null) {
            return new Result(200, "查询成功", sell, "查询成功");
        } else {
            return new Result(400, "查询失败", null, "商家不存在");
        }
    }
}
