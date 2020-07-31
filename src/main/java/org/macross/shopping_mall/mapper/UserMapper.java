package org.macross.shopping_mall.mapper;

import org.apache.ibatis.annotations.Param;
import org.macross.shopping_mall.model.entity.User;


public interface UserMapper {

    int register(User user);

    User login(@Param("phone")String phone,@Param("pwd") String pwd);

    User findUserInfoById(@Param("id") Integer user_id);

    int UpdateUserAccount(@Param("user_id")Integer userId,@Param("account")Integer account);
}
