package zjb.jtdx.qx.service;

import zjb.jtdx.qx.domain.User;
import zjb.jtdx.qx.utils.Result;

import java.util.List;

/**
 * 用户表服务层接口
 */
public interface UserService {

    //    获取所有用户
    Result getAllUsers();
    //    添加用户
    Result addUser(User user);
    //    删除用户
    Result deleteUser(int uid);

    // 修改用户
    Result updateUser(User user);
    //    登录
    Result loginUser(User user);
}
