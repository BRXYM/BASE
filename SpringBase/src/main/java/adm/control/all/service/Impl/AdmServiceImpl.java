package adm.control.all.service.Impl;

import adm.control.all.mapper.AdmMapper;
import adm.control.all.service.AdmService;
import adm.control.all.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdmServiceImpl implements AdmService {

    @Resource
    private AdmMapper admMapper;

    @Override
    public Result getTableInfo() {
        return Result.success(admMapper.getTableInfo(), "获取成功");
    }
}
