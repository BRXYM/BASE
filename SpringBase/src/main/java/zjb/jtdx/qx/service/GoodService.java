package zjb.jtdx.qx.service;

import zjb.jtdx.qx.domain.Good;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

public interface GoodService {
    Result getAllGoods();
    Result addGood(Good good);
    Result deleteGood(int goodId);
    Result updateGood(Good good);
    Result getGoodById(int goodId);
}
