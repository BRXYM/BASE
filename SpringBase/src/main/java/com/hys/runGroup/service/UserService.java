package com.hys.runGroup.service;

import com.hys.runGroup.domain.User;
import com.hys.runGroup.utils.Result;

public interface UserService {
    Result getAllUsers();

    Result addUser(User user);

    Result deleteUser(User user);

    Result updateUser(User user);

    Result loginUser(User user);
}
