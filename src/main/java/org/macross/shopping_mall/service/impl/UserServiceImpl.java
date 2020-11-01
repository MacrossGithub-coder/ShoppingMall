package org.macross.shopping_mall.service.impl;


import org.macross.shopping_mall.mapper.UserMapper;
import org.macross.shopping_mall.model.entity.User;
import org.macross.shopping_mall.model.request.LoginRequest;
import org.macross.shopping_mall.model.request.RegisterRequest;
import org.macross.shopping_mall.service.UserService;
import org.macross.shopping_mall.utils.CommonsUtils;
import org.macross.shopping_mall.utils.JWTUtils;
import org.macross.shopping_mall.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    @Qualifier("redisTemplateMaster")
    private RedisTemplate<String, Object> redisTemplateMaster;

    @Override
    public int register(RegisterRequest registerRequest) {

        if (registerRequest.getName() != null && registerRequest.getPhone() != null && registerRequest.getPwd() != null) {
            User user = new User();
            user.setName(registerRequest.getName());
            user.setPhone(registerRequest.getPhone());
            user.setPwd(CommonsUtils.MD5(registerRequest.getPwd()));
            user.setAccount(10000001);//初始金额为10000000分，100000元
            user.setAddress("中国");//初始地址为中国
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());

            return userMapper.register(user);
        }
        return -1;

    }

    @Override
    public String login(LoginRequest loginRequest) {

        User user = userMapper.login(loginRequest.getPhone(), CommonsUtils.MD5(loginRequest.getPwd()));

        //生成token
        if (user.getId() != null) {
            String token = JWTUtils.genericJsonWebToken(user);
            //存储token { token: userId } 过期时间为1周
            boolean result = redisUtil.setObj(token, user.getId(), 60 * 60 * 24 * 7);

//            单设备登录方案：
//            { userId ：token } 防止用户重复登录
//            boolean result2 = redisUtil.set(user.getId().toString(),token);
//            return result && result2 ? token:null;

            Long size = redisTemplateMaster.opsForList().size(user.getId().toString());
            //多设备登录方案：表示允许number台设备同时登录,大于number则将最早登录的设备挤出
            int number = 2;
            if (size > number - 1) {
                String discard_token = (String) redisTemplateMaster.opsForList().rightPop(user.getId().toString());
                redisTemplateMaster.delete(discard_token);
            }
            redisTemplateMaster.opsForList().leftPush(user.getId().toString(), token);
            return token;
        }
        return null;
    }

    @Override
    public User findUserInfoById(Integer user_id) {

        User user = userMapper.findUserInfoById(user_id);
        return user;
    }

    @Override
    public boolean logout(Integer userId, String accessToken) {
        try {
            String temp_token = null;
            List<String> list = new ArrayList<>();
            while (!accessToken.equals(temp_token = (String) redisTemplateMaster.opsForList().rightPop(userId.toString()))) {
                list.add(temp_token);
            }
            int size = list.size();
            if (size > 0) {
                for (int i = size - 1; i >= 0; i--) {
                    redisTemplateMaster.opsForList().rightPush(userId.toString(), list.get(i));
                }
            }
            return redisTemplateMaster.delete(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 随机头像
     */
    private static final String[] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg() {

        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }


}
