package com.hys.runGroup.service;

import com.hys.runGroup.domain.Stow;
import com.hys.runGroup.utils.Result;

/**
 * 收藏业务层接口
 */
public interface StowService {
    // 获取所有收藏信息
    Result getAllStows();

    // 根据id获取收藏
    Result getStowById(int id);

    // 根据用户id获取收藏信息
    Result getStowsByUid(int Uid);

    // 添加收藏
    Result addStow(Stow stow);

    // 删除收藏
    Result deleteStow(Stow stow);

    // 修改收藏
    Result updateStow(Stow stow);
}