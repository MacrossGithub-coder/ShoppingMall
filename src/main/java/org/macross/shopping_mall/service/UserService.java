package org.macross.shopping_mall.service;

import org.macross.shopping_mall.model.entity.User;
import org.macross.shopping_mall.model.request.LoginRequest;
import org.macross.shopping_mall.model.request.RegisterRequest;

public interface UserService {

    int register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);

    User findUserInfoById(Integer user_id);
}
