package zjb.jtdx.qx.service;

import zjb.jtdx.qx.domain.Ord;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

public interface OrdService {
    Result getAllOrds();
    Result addOrd(Ord ord);
    Result deleteOrd(int ordId);
    Result updateOrd(Ord ord);
    Result getOrdById(int ordId);
    Result getOrdsByUserId(int userId);
}
