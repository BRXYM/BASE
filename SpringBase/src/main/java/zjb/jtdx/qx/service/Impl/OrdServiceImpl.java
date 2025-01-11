package zjb.jtdx.qx.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import zjb.jtdx.qx.domain.Ord;
import zjb.jtdx.qx.mapper.OrdMapper;
import zjb.jtdx.qx.service.OrdService;
import org.springframework.stereotype.Service;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

@Service
public class OrdServiceImpl implements OrdService {

    @Resource
    private OrdMapper ordMapper;

    @Override
    public Result getAllOrds() {
        List<Ord> list = ordMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    @Override
    public Result addOrd(Ord ord) {
        int i = ordMapper.insert(ord);
        if (i > 0) {
            return Result.success(ordMapper.selectById(ord.getOrdId()), "添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @Override
    public Result deleteOrd(int ordId) {
        Ord ord = ordMapper.selectById(ordId);
        if (ord == null) {
            return Result.fail("对象不存在");
        }
        int i = ordMapper.deleteById(ordId);
        if (i > 0) {
            return Result.success(i, "删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result updateOrd(Ord ord) {
        Ord updateOrd = ordMapper.selectById(ord.getOrdId());
        if (updateOrd == null) {
            return Result.fail("对象不存在");
        }
        int i = ordMapper.updateById(ord);
        if (i > 0) {
            return Result.success(ordMapper.selectById(ord.getOrdId()), "修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @Override
    public Result getOrdById(int ordId) {
        Ord ord = ordMapper.selectById(ordId);
        if (ord != null) {
            return Result.success(ord, "获取成功");
        } else {
            return Result.fail("对象不存在");
        }
    }
}
