package com.hys.runGroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hys.runGroup.domain.Mode;
import com.hys.runGroup.mapper.ModeMapper;
import com.hys.runGroup.service.ModeService;
import com.hys.runGroup.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 模组表实现
 */
@Service
public class ModeServiceImpl implements ModeService {

    @Resource
    private ModeMapper modeMapper;

    /**
     * 增加模组
     * @param mode - 模组对象
     * @param file - 上传的文件
     * @return Result - 添加模组的结果
     */
    @Override
    public Result addMode(Mode mode, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String fileName = file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                file.transferTo(filePath.toFile());
                mode.setFile(fileName);
            } catch (IOException e) {
                return Result.fail("文件上传失败");
            }
        }
        int insert = modeMapper.insert(mode);
        if (insert > 0) {
            return Result.success(mode, "添加成功");
        } else {
            return Result.fail("添加模组失败");
        }
    }

    /**
     * 删除模组
     * @param mode - 包含MOid的模组对象
     * @return Result - 删除模组的结果
     */
    @Override
    public Result deleteMode(Mode mode) {
        int delete = modeMapper.deleteById(mode.getMOid());
        if (delete > 0) {
            return Result.success(null, "删除成功");
        } else {
            return Result.fail("删除模组失败");
        }
    }

    /**
     * 获取所有模组
     * @return Result<List<Mode>> - 包含所有模组的Result对象
     */
    @Override
    public Result getAllModes() {
        List<Mode> list = modeMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 修改模组
     * @param mode - 模组对象
     * @param file - 上传的文件
     * @return Result - 更新模组的结果
     */
    @Override
    public Result updateMode(Mode mode, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String fileName = file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                file.transferTo(filePath.toFile());
                mode.setFile(fileName);
            } catch (IOException e) {
                return Result.fail("文件上传失败");
            }
        }
        int update = modeMapper.updateById(mode);
        if (update > 0) {
            return Result.success(mode, "更新成功");
        } else {
            return Result.fail("更新模组失败");
        }
    }

    /**
     * 根据上传者id获取mode
     * @param Uid - 用户ID
     * @return Result<List<Mode>> - 包含指定用户ID的模组列表的Result对象
     */
    @Override
    public Result getModesByUid(int Uid) {
        List<Mode> list = modeMapper.selectList(new QueryWrapper<Mode>().eq("hysMOidU", Uid));
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 根据mode id获取mode，并更新收藏数
     * @param MOid - 模组ID
     * @return Result<Mode> - 包含更新后的模组对象的Result对象
     */
    @Override
    public Result getModeByIdAndUpdateSum(int MOid) {
        Mode mode = modeMapper.selectById(MOid);
        if (mode != null) {
            mode.setSum(mode.getSum() + 1);
            int update = modeMapper.updateById(mode);
            if (update > 0) {
                return Result.success(mode, "更新成功");
            } else {
                return Result.fail("更新模组失败");
            }
        } else {
            return Result.fail("模组不存在");
        }
    }

    /**
     * 根据类id获取mode
     * @param Tid - 类别ID
     * @return Result<List<Mode>> - 包含指定类别ID的模组列表的Result对象
     */
    @Override
    public Result getModesByTid(int Tid) {
        List<Mode> list = modeMapper.selectList(new QueryWrapper<Mode>().eq("hysMOidT", Tid));
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }
}
