package com.hys.runGroup.service;

import com.hys.runGroup.domain.Mode;
import com.hys.runGroup.utils.Result;
import org.springframework.web.multipart.MultipartFile;

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
    Result updateMode(Mode mode, MultipartFile file);

    // 根据上传者id获取mode
    Result getModesByUid(int Uid);

    // 根据mode id获取mode，并更新收藏数
    Result getModeByIdAndUpdateSum(int MOid);

    // 根据类id获取mode
    Result getModesByTid(int Tid);
}