package zjb.jtdx.qx.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import zjb.jtdx.qx.domain.User;
import zjb.jtdx.qx.mapper.UserMapper;
import zjb.jtdx.qx.service.UserService;
import org.springframework.stereotype.Service;
import zjb.jtdx.qx.utils.Result;

import java.util.List;
/**
 * 用户表实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 获取所有用户
     * @return Result<List<User>> - 包含所有用户信息的Result对象
     */
    @Override
    public Result getAllUsers() {
        List<User> list = userMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return Result.success(list, "获取成功");
        } else {
            return Result.fail("列表为空");
        }
    }

    /**
     * 增加用户
     * @param user - 用户对象
     * @return Result<User> - 添加用户的结果，包含添加后的用户对象
     */
    @Override
    public Result addUser(User user) {
        // 查询电话号是否存在
        User existingUser = userMapper.selectOne(new QueryWrapper<User>().eq("user_phone", user.getUser_phone()));
        if (existingUser != null) {
            return Result.fail("电话号已经存在");
        }
        int i = userMapper.insert(user);
        if (i > 0) {
            return Result.success(userMapper.selectById(user.getUser_id()), "添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    /**
     * 删除用户
     * @param uid - 用户ID
     * @return Result<Integer> - 删除用户的结果，包含删除的记录数
     */
    @Override
    public Result deleteUser(int uid) {
        User newUser = userMapper.selectById(uid);
        if (newUser == null) {
            return Result.fail("对象不存在");
        }
        int i = userMapper.deleteById(uid);
        if (i > 0) {
            return Result.success(i, "删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 修改用户
     * @param user - 用户对象
     * @return Result<User> - 更新用户的结果，包含更新后的用户对象
     */
    @Override
    public Result updateUser(User user) {
        User updateUser = userMapper.selectById(user.getUser_id());
        if (updateUser == null) {
            return Result.fail("对象不存在");
        }
        int i = userMapper.updateById(user);
        if (i > 0) {
            return Result.success(userMapper.selectById(user.getUser_id()), "修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    /**
     * 用户登录
     * @param user - 包含用户ID和密码的用户对象
     * @return Result<User> - 登录结果，包含用户对象
     */
    @Override
    public Result loginUser(User user) {
        User login = userMapper.selectOne(new QueryWrapper<User>().eq("user_phone", user.getUser_phone()));
        if (login == null) {
            return Result.fail("用户手机号错误");
        }
        // 优化: 避免两次查询数据库
        if (login.getUser_pass().equals(user.getUser_pass())) {
            return Result.success(login, "登陆成功");
        } else {
            return Result.fail("密码错误");
        }
    }
}

