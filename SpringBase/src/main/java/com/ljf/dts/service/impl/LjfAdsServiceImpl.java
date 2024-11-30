package com.ljf.dts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljf.dts.domain.LjfAds;
import com.ljf.dts.service.LjfAdsService;
import com.ljf.dts.mapper.LjfAdsMapper;
import com.ljf.dts.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjfAdsServiceImpl extends ServiceImpl<LjfAdsMapper, LjfAds>
    implements LjfAdsService {

    @Override
    public Result getAllAds() {
        List<LjfAds> adsList = list();
        return new Result(200, "查询成功", adsList, "查询成功");
    }

    @Override
    public Result addAd(LjfAds ad) {
        boolean save = save(ad);
        if (save) {
            return new Result(200, "添加成功", ad, "添加成功");
        } else {
            return new Result(400, "添加失败", null, "添加失败");
        }
    }

    @Override
    public Result updateAd(LjfAds ad) {
        boolean update = updateById(ad);
        if (update) {
            return new Result(200, "更新成功", ad, "更新成功");
        } else {
            return new Result(400, "更新失败", null, "更新失败");
        }
    }

    @Override
    public Result deleteAd(Integer id) {
        boolean delete = removeById(id);
        if (delete) {
            return new Result(200, "删除成功", null, "删除成功");
        } else {
            return new Result(400, "删除失败", null, "删除失败");
        }
    }

    @Override
    public Result getAdById(Integer id) {
        LjfAds ad = getById(id);
        if (ad != null) {
            return new Result(200, "查询成功", ad, "查询成功");
        } else {
            return new Result(400, "查询失败", null, "地址不存在");
        }
    }
}
