package com.ljf.dts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljf.dts.domain.LjfGood;
import com.ljf.dts.mapper.LjfOrderMapper;
import com.ljf.dts.service.LjfGoodService;
import com.ljf.dts.mapper.LjfGoodMapper;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjfGoodServiceImpl extends ServiceImpl<LjfGoodMapper, LjfGood>
    implements LjfGoodService {
    @Autowired
    private LjfGoodMapper ljfgoodMapper;
    @Override
    public Result getAllGoods() {
        List<LjfGood> goodsList = list();
        return new Result(200, "查询成功", goodsList, "查询成功");
    }

    @Override
    public Result addGood(LjfGood good) {
        boolean save = save(good);
        if (save) {
            return new Result(200, "添加成功", good, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result updateGood(LjfGood good) {
        boolean update = updateById(good);
        if (update) {
            return new Result(200, "更新成功", good, "更新成功");
        } else {
            return new Result(400, "更新失败", null, "更新失败");
        }
    }

    @Override
    public Result deleteGood(Integer id) {
        boolean delete = removeById(id);
        if (delete) {
            return new Result(200, "删除成功", null, "删除成功");
        } else {
            return new Result(400, "删除失败", null, "删除失败");
        }
    }

    @Override
    public Result getGoodById(Integer id) {
        LjfGood good = ljfgoodMapper.getByGoodId(id);
        if (good != null) {
            return new Result(200, "查询成功", good, "查询成功");
        } else {
            return new Result(400, "查询失败", null, "商品不存在");
        }
    }
}
