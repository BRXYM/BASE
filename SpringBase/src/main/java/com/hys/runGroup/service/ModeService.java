package com.hys.runGroup.service;

import com.hys.runGroup.domain.Mode;
import com.hys.runGroup.utils.Result;

/**
 * 模组表情业务层接口
 */
public interface ModeService {
//    新增模组
    Result addMode(Mode mode);
//    删除模组
    Result deleteMode(Mode mode);
//    获取所有模组
    Result getAllModes();
//    更新模组
    Result updateMode(Mode mode);
}
