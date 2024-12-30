package com.hys.runGroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hys.runGroup.domain.Mode;
import com.hys.runGroup.domain.Stow;
import com.hys.runGroup.mapper.ModeMapper;
import com.hys.runGroup.mapper.StowMapper;
import com.hys.runGroup.service.StowService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏表实现
 */
@Service
public class StowServiceImpl implements StowService {

    @Resource
    private StowMapper stowMapper;
    @Resource
    private ModeMapper modeMapper;

    /**
     * 获取所有收藏信息
     * @return Result<List<Stow>> - 包含所有收藏信息的Result对象
     */
    @Override
    public Result getAllStows() {
        List<Stow> list = stowMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 根据id获取收藏
     * @param id - 收藏ID
     * @return Result<Stow> - 包含指定收藏ID的收藏对象的Result对象
     */
    @Override
    public Result getStowById(int id) {
        Stow stow = stowMapper.selectById(id);
        if (stow != null) {
            return Result.success(stow, "获取成功");
        } else {
            return Result.fail("收藏不存在");
        }
    }

    /**
     * 根据用户id获取收藏信息
     * @param Uid - 用户ID
     * @return Result<List<Stow>> - 包含指定用户ID的收藏列表的Result对象
     */
    @Override
    public Result getStowsByUid(int Uid) {
        List<Stow> list = stowMapper.selectList(new QueryWrapper<Stow>().eq("hysSidU", Uid));
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 添加收藏
     * @param stow - 收藏对象
     * @return Result - 添加收藏的结果
     */
@Override
public Result addStow(Stow stow) {
    int insert = stowMapper.insert(stow);
    if (insert > 0) {
        // 插入成功后，根据stow中的moid 更新mode表中的sum字段让sum字段+1
        int moid = stow.getMOid();
        Mode mode = modeMapper.selectById(moid);
        if (mode != null) {
            mode.setSum(mode.getSum() + 1);
            modeMapper.updateById(mode);
        } else {
            return Result.fail("对应的模组不存在");
        }
        return Result.success(stow, "添加成功");
    } else {
        return Result.fail("添加收藏失败");
    }
}


    /**
     * 删除收藏
     * @param stow - 包含Sid的收藏对象
     * @return Result - 删除收藏的结果
     */
    @Override
    public Result deleteStow(Stow stow) {
        int delete = stowMapper.deleteById(stow.getSid());
        if (delete > 0) {
            return Result.success(null, "删除成功");
        } else {
            return Result.fail("删除收藏失败");
        }
    }

    /**
     * 修改收藏
     * @param stow - 收藏对象
     * @return Result - 更新收藏的结果
     */
    @Override
    public Result updateStow(Stow stow) {
        int update = stowMapper.updateById(stow);
        if (update > 0) {
            return Result.success(stow, "更新成功");
        } else {
            return Result.fail("更新收藏失败");
        }
    }
}
