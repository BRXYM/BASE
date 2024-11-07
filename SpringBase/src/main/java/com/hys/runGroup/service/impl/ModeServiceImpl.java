package com.hys.runGroup.service.impl;

import com.hys.runGroup.domain.Mode;
import com.hys.runGroup.mapper.ModeMapper;
import com.hys.runGroup.service.ModeService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模组表实现
 */
@Service
public class ModeServiceImpl implements ModeService {

    @Resource
    private ModeMapper modeMapper;

    @Override
    public Result addMode(Mode mode) {
        return null;
    }

    @Override
    public Result deleteMode(Mode mode) {
        return null;
    }

    @Override
    public Result getAllModes() {
        List<Mode>  list = modeMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return new Result(200, "001", list, "获取成功");
        }else {
            return new Result(200, "002", list, "列表为空");
        }
    }

    @Override
    public Result updateMode(Mode mode) {
        return null;
    }
}
