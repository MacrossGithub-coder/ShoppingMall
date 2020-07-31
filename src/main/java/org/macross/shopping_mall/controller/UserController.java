package org.macross.shopping_mall.controller;

import org.macross.shopping_mall.model.entity.User;
import org.macross.shopping_mall.model.request.LoginRequest;
import org.macross.shopping_mall.model.request.RegisterRequest;
import org.macross.shopping_mall.service.UserService;
import org.macross.shopping_mall.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("register")
    public JsonData register(@RequestBody RegisterRequest registerRequest){
        int result = userService.register(registerRequest);
        return result == 1 ? JsonData.buildSuccess("注册成功"):JsonData.buildError("注册失败");
    }

    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){

        String token = userService.login(loginRequest);
        return token != null ? JsonData.buildSuccess(token):JsonData.buildError("登录失败");
    }

    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){

        Integer user_id = (Integer)request.getAttribute("user_id");
        User user = userService.findUserInfoById(user_id);
        return user != null ? JsonData.buildSuccess(user):JsonData.buildError("查询失败！");

    }

}
