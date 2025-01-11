package zjb.jtdx.qx.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import zjb.jtdx.qx.domain.Cla;
import zjb.jtdx.qx.mapper.ClaMapper;
import zjb.jtdx.qx.service.ClaService;
import org.springframework.stereotype.Service;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

@Service
public class ClaServiceImpl implements ClaService {

    @Resource
    private ClaMapper claMapper;

    @Override
    public Result getAllClas() {
        List<Cla> list = claMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    @Override
    public Result addCla(Cla cla) {
        int i = claMapper.insert(cla);
        if (i > 0) {
            return Result.success(claMapper.selectById(cla.getClaId()), "添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @Override
    public Result deleteCla(int claId) {
        Cla cla = claMapper.selectById(claId);
        if (cla == null) {
            return Result.fail("对象不存在");
        }
        int i = claMapper.deleteById(claId);
        if (i > 0) {
            return Result.success(i, "删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result updateCla(Cla cla) {
        Cla updateCla = claMapper.selectById(cla.getClaId());
        if (updateCla == null) {
            return Result.fail("对象不存在");
        }
        int i = claMapper.updateById(cla);
        if (i > 0) {
            return Result.success(claMapper.selectById(cla.getClaId()), "修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @Override
    public Result getClaById(int claId) {
        Cla cla = claMapper.selectById(claId);
        if (cla != null) {
            return Result.success(cla, "获取成功");
        } else {
            return Result.fail("对象不存在");
        }
    }
}
