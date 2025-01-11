package zjb.jtdx.qx.service;

import zjb.jtdx.qx.domain.Cla;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

public interface ClaService {
    Result getAllClas();
    Result addCla(Cla cla);
    Result deleteCla(int claId);
    Result updateCla(Cla cla);
    Result getClaById(int claId);
}