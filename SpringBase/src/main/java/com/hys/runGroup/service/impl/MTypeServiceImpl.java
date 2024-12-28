package com.hys.runGroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hys.runGroup.domain.MType;
import com.hys.runGroup.mapper.MTypeMapper;
import com.hys.runGroup.service.MTypeService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类表实现
 */
@Service
public class MTypeServiceImpl implements MTypeService {

    @Resource
    private MTypeMapper mTypeMapper;

    /**
     * 获取所有分类信息
     * @return Result<List<MType>> - 包含所有分类信息的Result对象
     */
    @Override
    public Result getAllMTypes() {
        List<MType> list = mTypeMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 根据id获取分类名
     * @param id - 分类ID
     * @return Result<MType> - 包含指定分类ID的分类对象的Result对象
     */
    @Override
    public Result getMTypeById(int id) {
        MType mType = mTypeMapper.selectById(id);
        if (mType != null) {
            return Result.success(mType, "获取成功");
        } else {
            return Result.fail("分类不存在");
        }
    }
}
