package com.ljf.dts.controller;

import com.ljf.dts.domain.LjfAds;
import com.ljf.dts.service.LjfAdsService;
import com.ljf.dts.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads")
public class LjfAdsController {

    @Autowired
    private LjfAdsService ljfAdsService;

    @GetMapping
    public Result getAds() {
        // Assuming there's a method to get all ads
        return ljfAdsService.getAllAds();
    }

    @PostMapping("/add")
    public Result addAd(@RequestBody LjfAds ad) {
        return ljfAdsService.addAd(ad);
    }

    @PostMapping("/update")
    public Result updateAd(@RequestBody LjfAds ad) {
        return ljfAdsService.updateAd(ad);
    }

    @PostMapping("/delete")
    public Result deleteAd(@RequestBody LjfAds ad) {
        return ljfAdsService.deleteAd(ad.getAds_id());
    }
}
