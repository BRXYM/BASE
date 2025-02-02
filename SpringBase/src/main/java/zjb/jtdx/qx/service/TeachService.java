package zjb.jtdx.qx.service;

import zjb.jtdx.qx.domain.Teach;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

public interface TeachService {
    Result getAllTeaches();
    Result addTeach(Teach teach);
    Result deleteTeach(int teachId);
    Result updateTeach(Teach teach);
    Result getTeachById(int teachId);
    Result login(String teachPhone, String teachPass);
}
