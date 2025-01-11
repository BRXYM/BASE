package zjb.jtdx.qx.service.Impl;

import zjb.jtdx.qx.domain.User;
import zjb.jtdx.qx.mapper.UserMapper;
import zjb.jtdx.qx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.test.example.utils.Result;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getUserById(Integer id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            return new Result(200, "成功", user, "用户找到");
        } else {
            return new Result(404, "未找到", "用户未找到");
        }
    }

    @Override
    public Result getAllUsers() {
        List<User> users = userMapper.selectList(null);
        return new Result(200, "成功", users, "用户列表获取成功");
    }

    @Override
    public Result addUser(User user) {
        userMapper.insert(user);
        return new Result(200, "创建成功", "用户添加成功");
    }

    @Override
    public Result updateUser(User user) {
        userMapper.updateById(user);
        return new Result(200, "成功", "用户更新成功");
    }

    @Override
    public Result deleteUser(Integer id) {
        userMapper.deleteById(id);
        return new Result(200, "成功", "用户删除成功");
    }
}
