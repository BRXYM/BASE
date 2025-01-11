package zjb.jtdx.qx.service;

import zjb.jtdx.qx.domain.Sela;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

public interface SelaService {
    Result getAllSelas();
    Result addSela(Sela sela);
    Result deleteSela(int selaId);
    Result updateSela(Sela sela);
    Result getSelaById(int selaId);
}
