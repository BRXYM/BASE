package zjb.jtdx.qx.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import zjb.jtdx.qx.domain.Teach;
import zjb.jtdx.qx.mapper.TeachMapper;
import zjb.jtdx.qx.service.TeachService;
import org.springframework.stereotype.Service;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeachService {

    @Resource
    private TeachMapper teachMapper;

    @Override
    public Result getAllTeaches() {
        List<Teach> list = teachMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    @Override
    public Result addTeach(Teach teach) {
        int i = teachMapper.insert(teach);
        if (i > 0) {
            return Result.success(teachMapper.selectById(teach.getTeachId()), "添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @Override
    public Result deleteTeach(int teachId) {
        Teach teach = teachMapper.selectById(teachId);
        if (teach == null) {
            return Result.fail("对象不存在");
        }
        int i = teachMapper.deleteById(teachId);
        if (i > 0) {
            return Result.success(i, "删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result updateTeach(Teach teach) {
        Teach updateTeach = teachMapper.selectById(teach.getTeachId());
        if (updateTeach == null) {
            return Result.fail("对象不存在");
        }
        int i = teachMapper.updateById(teach);
        if (i > 0) {
            return Result.success(teachMapper.selectById(teach.getTeachId()), "修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @Override
    public Result getTeachById(int teachId) {
        Teach teach = teachMapper.selectById(teachId);
        if (teach != null) {
            return Result.success(teach, "获取成功");
        } else {
            return Result.fail("对象不存在");
        }
    }

    @Override
    public Result login(String teachPhone, String teachPass) {
        QueryWrapper<Teach> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_phone", teachPhone).eq("teach_pass", teachPass);
        Teach teach = teachMapper.selectOne(queryWrapper);

        if (teach != null) {
            return Result.success(teach, "登录成功");
        } else {
            return Result.fail("手机号或密码错误");
        }
    }
}
