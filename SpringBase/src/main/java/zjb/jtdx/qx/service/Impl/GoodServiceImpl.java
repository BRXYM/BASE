package zjb.jtdx.qx.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import zjb.jtdx.qx.domain.Good;
import zjb.jtdx.qx.mapper.GoodMapper;
import zjb.jtdx.qx.service.GoodService;
import org.springframework.stereotype.Service;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodMapper goodMapper;

    @Override
    public Result getAllGoods() {
        List<Good> list = goodMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    @Override
    public Result addGood(Good good) {
        int i = goodMapper.insert(good);
        if (i > 0) {
            return Result.success(goodMapper.selectById(good.getGoodId()), "添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @Override
    public Result deleteGood(int goodId) {
        Good good = goodMapper.selectById(goodId);
        if (good == null) {
            return Result.fail("对象不存在");
        }
        int i = goodMapper.deleteById(goodId);
        if (i > 0) {
            return Result.success(i, "删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result updateGood(Good good) {
        Good updateGood = goodMapper.selectById(good.getGoodId());
        if (updateGood == null) {
            return Result.fail("对象不存在");
        }
        int i = goodMapper.updateById(good);
        if (i > 0) {
            return Result.success(goodMapper.selectById(good.getGoodId()), "修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @Override
    public Result getGoodById(int goodId) {
        Good good = goodMapper.selectById(goodId);
        if (good != null) {
            return Result.success(good, "获取成功");
        } else {
            return Result.fail("对象不存在");
        }
    }
}
