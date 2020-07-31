package org.macross.shopping_mall.service.impl;


import org.macross.shopping_mall.mapper.UserMapper;
import org.macross.shopping_mall.model.entity.User;
import org.macross.shopping_mall.model.request.LoginRequest;
import org.macross.shopping_mall.model.request.RegisterRequest;
import org.macross.shopping_mall.service.UserService;
import org.macross.shopping_mall.utils.CommonsUtils;
import org.macross.shopping_mall.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public int register(RegisterRequest registerRequest) {

        if(registerRequest.getName() !=null && registerRequest.getPhone() !=null && registerRequest.getPwd() !=null){
            User user = new User();
            user.setName(registerRequest.getName());
            user.setPhone(registerRequest.getPhone());
            user.setPwd(CommonsUtils.MD5(registerRequest.getPwd()));
            user.setAccount(10000001);//初始金额为10000000分，100000元
            user.setAddress("中国");//初始地址为中国
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());

            int result = userMapper.register(user);
            return result;
        }
        return -1;

    }

    @Override
    public String login(LoginRequest loginRequest) {

        User user = userMapper.login(loginRequest.getPhone(),CommonsUtils.MD5(loginRequest.getPwd()));

        //生成token
        if(user.getId() != null){
            String token = JWTUtils.genericJsonWebToken(user);
            return token;
        }
        return null;

    }

    @Override
    public User findUserInfoById(Integer user_id) {

        User user = userMapper.findUserInfoById(user_id);

        if (user != null){
            return user;
        }
        return null;
    }


    /**
     * 随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){

        int size = headImg.length;
        Random random = new Random();
        int index =  random.nextInt(size);
        return headImg[index];
    }


}