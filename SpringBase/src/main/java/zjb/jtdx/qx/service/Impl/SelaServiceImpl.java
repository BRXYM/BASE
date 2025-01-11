package zjb.jtdx.qx.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import zjb.jtdx.qx.domain.Sela;
import zjb.jtdx.qx.mapper.SelaMapper;
import zjb.jtdx.qx.service.SelaService;
import org.springframework.stereotype.Service;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

@Service
public class SelaServiceImpl implements SelaService {

    @Resource
    private SelaMapper selaMapper;

    @Override
    public Result getAllSelas() {
        List<Sela> list = selaMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    @Override
    public Result addSela(Sela sela) {
        int i = selaMapper.insert(sela);
        if (i > 0) {
            return Result.success(selaMapper.selectById(sela.getSelaId()), "添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @Override
    public Result deleteSela(int selaId) {
        Sela sela = selaMapper.selectById(selaId);
        if (sela == null) {
            return Result.fail("对象不存在");
        }
        int i = selaMapper.deleteById(selaId);
        if (i > 0) {
            return Result.success(i, "删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result updateSela(Sela sela) {
        Sela updateSela = selaMapper.selectById(sela.getSelaId());
        if (updateSela == null) {
            return Result.fail("对象不存在");
        }
        int i = selaMapper.updateById(sela);
        if (i > 0) {
            return Result.success(selaMapper.selectById(sela.getSelaId()), "修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @Override
    public Result getSelaById(int selaId) {
        Sela sela = selaMapper.selectById(selaId);
        if (sela != null) {
            return Result.success(sela, "获取成功");
        } else {
            return Result.fail("对象不存在");
        }
    }
}
