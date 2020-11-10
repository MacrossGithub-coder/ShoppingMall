package org.macross.shopping_mall.service.impl;

import org.macross.shopping_mall.exception.RunTimeException;
import org.macross.shopping_mall.mapper.CommodityMapper;
import org.macross.shopping_mall.mapper.CommodityOrderMapper;
import org.macross.shopping_mall.mapper.UserMapper;
import org.macross.shopping_mall.model.entity.Commodity;
import org.macross.shopping_mall.model.entity.CommodityOrder;
import org.macross.shopping_mall.model.entity.CommoditySeckill;
import org.macross.shopping_mall.model.entity.User;
import org.macross.shopping_mall.service.CommodityOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommodityOrderServiceImpl implements CommodityOrderService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityOrderMapper commodityOrderMapper;

    /**
     * 返回JsonData
     * code: -1 数据库用户信息有误
     * code: -2 数据库商品信息有误
     * code: -3 余额不足
     * code: 0 正常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int commodityOrder(Integer userId, Integer commodityId)  {

        //查询用户信息
        User userInfo = userMapper.findUserInfoById(userId);
        //查询商品信息
        Commodity commodityDetail = commodityMapper.findCommodityDetail(commodityId);

        if (userInfo == null){
            throw new RunTimeException(-1,"数据库用户信息有误，请联系开发者");
        }
        if (commodityDetail == null){
            throw new RunTimeException(-2,"数据库商品信息有误，请联系开发者");
        }

        //修改账户余额
        Integer orgAccount = userInfo.getAccount();
        Integer finalAccount = orgAccount - commodityDetail.getPrice();

        if (finalAccount < 0){
            throw new RunTimeException(-3,"余额不足");
        }

        CommodityOrder commodityOrder = new CommodityOrder();
        commodityOrder.setOutTradeNo(UUID.randomUUID().toString());
        commodityOrder.setUserId(userInfo.getId());
        commodityOrder.setState(1);
        commodityOrder.setTotalFee(commodityDetail.getPrice());
        commodityOrder.setCommodityId(commodityDetail.getId());
        commodityOrder.setCommodityDescribe(commodityDetail.getDescribe());
        commodityOrder.setCommodityImg(commodityDetail.getHomeImg());
        commodityOrder.setAddress(userInfo.getAddress());
        commodityOrder.setCreateTime(new Date());


        //插入Order表
        int result = commodityOrderMapper.commodityOrder(commodityOrder);


        //修改账户余额
        userMapper.UpdateUserAccount(userInfo.getId(), finalAccount);

        return result;
    }




    @Override
    public List<CommodityOrder> findUserOrder(Integer userId) {

        List<CommodityOrder> userCommodityOrder = commodityOrderMapper.findUserOrder(userId);

        return userCommodityOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int seckillCommodityOrder(Integer userId, CommoditySeckill commoditySeckill) {

        //查询用户信息
        User userInfo = userMapper.findUserInfoById(userId);
        //查询商品信息
        Commodity commodityDetail = commodityMapper.findCommodityDetail(commoditySeckill.getCommodityId());

        if (userInfo == null){
            throw new RunTimeException(-1,"数据库用户信息有误，请联系开发者");
        }
        if (commodityDetail == null){
            throw new RunTimeException(-2,"数据库商品信息有误，请联系开发者");
        }

        //修改账户余额
        Integer orgAccount = userInfo.getAccount();
        Integer finalAccount = orgAccount - commoditySeckill.getSeckillPrice();

        if (finalAccount < 0){
            throw new RunTimeException(-3,"余额不足");
        }

        CommodityOrder commodityOrder = new CommodityOrder();
        commodityOrder.setOutTradeNo(UUID.randomUUID().toString());
        commodityOrder.setUserId(userInfo.getId());
        commodityOrder.setState(1);
        commodityOrder.setTotalFee(commoditySeckill.getSeckillPrice());
        commodityOrder.setCommodityId(commodityDetail.getId());
        commodityOrder.setCommodityDescribe("[秒杀价]"+commodityDetail.getDescribe());
        commodityOrder.setCommodityImg(commodityDetail.getHomeImg());
        commodityOrder.setAddress(userInfo.getAddress());
        commodityOrder.setCreateTime(new Date());


        //插入Order表
        int result = commodityOrderMapper.commodityOrder(commodityOrder);


        //修改账户余额
        userMapper.UpdateUserAccount(userInfo.getId(), finalAccount);

        return commodityOrder.getId();
    }
}
