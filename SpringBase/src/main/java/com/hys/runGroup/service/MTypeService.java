package com.hys.runGroup.service;

import com.hys.runGroup.domain.MType;
import com.hys.runGroup.utils.Result;

/**
 * 分类业务层接口
 */
public interface MTypeService {
    // 获取所有分类信息
    Result getAllMTypes();

    // 根据id获取分类名
    Result getMTypeById(int id);
}
