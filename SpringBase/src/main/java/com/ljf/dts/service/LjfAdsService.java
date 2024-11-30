package com.ljf.dts.service;

import com.ljf.dts.domain.LjfAds;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.dts.utils.Result;

public interface LjfAdsService extends IService<LjfAds> {
    Result getAllAds();
    Result addAd(LjfAds ad);
    Result updateAd(LjfAds ad);
    Result deleteAd(Integer id);
    Result getAdById(Integer id);
}
