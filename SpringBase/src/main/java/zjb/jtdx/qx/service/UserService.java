package zjb.jtdx.qx.service;

import zjb.jtdx.qx.domain.User;
import java.util.List;
import com.test.example.utils.Result;

public interface UserService {
    Result getUserById(Integer id);
    Result getAllUsers();
    Result addUser(User user);
    Result updateUser(User user);
    Result deleteUser(Integer id);

}
