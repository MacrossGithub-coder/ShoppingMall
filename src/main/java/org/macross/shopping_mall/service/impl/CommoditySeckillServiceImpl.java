package org.macross.shopping_mall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.macross.shopping_mall.mapper.CommoditySeckillMapper;
import org.macross.shopping_mall.model.entity.CommoditySeckill;
import org.macross.shopping_mall.model.entity.SeckillOrder;
import org.macross.shopping_mall.rabbitmq.SeckillMessage;
import org.macross.shopping_mall.service.CommodityOrderService;
import org.macross.shopping_mall.service.CommoditySeckillService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class CommoditySeckillServiceImpl implements CommoditySeckillService {

    @Autowired
    @Qualifier("redisTemplateMasterSeckill")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CommoditySeckillMapper commoditySeckillMapper;

    @Autowired
    private CommodityOrderService commodityOrderService;

    @Autowired
    public RabbitTemplate rabbitTemplate;

    /**
     * -1 ：库存不足秒杀失败
     * -2 : 该用户存在重复下单行为
     * 0 ：排队中，继续轮询
     */
    private static final int SECKILL_ERROR = -1;
    private static final int REPEATED_SECKILL = -2;
    private static final int ADD_QUEUE = 0;

    private static final String COMMODITY_OVER = "commodity_over:";

    /**
     * 商品秒杀
     *
     * @param commodityId
     * @param userId
     * @return
     */
    @Override
    public int doCommoditySeckill(Integer commodityId, Integer userId) throws JsonProcessingException {

        //在Redis中进行库存预减
        Long stock = redisTemplate.opsForValue().decrement(commodityId.toString());
        if (stock != null && stock < 0) {
            return SECKILL_ERROR;
        }

        //判断用户是否重复下单
        SeckillOrder seckillOrder = commoditySeckillMapper.findSeckillOrder(userId, commodityId);
        if (seckillOrder != null) {
            redisTemplate.opsForValue().increment(commodityId.toString());
            return REPEATED_SECKILL;
        }

        //正常请求，加入mq
        SeckillMessage seckillMessage = new SeckillMessage(userId, commodityId);
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(seckillMessage);
        rabbitTemplate.convertAndSend("seckill_queue", "seckill", message);
        return ADD_QUEUE;
    }

    /**
     * 1.库存-1 2.下订单 3.下秒杀订单
     *
     * @param userId
     * @param commodityId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSeckillService(Integer userId, CommoditySeckill commoditySeckill) {

        int state = commoditySeckillMapper.reduceStock(commoditySeckill.getCommodityId());
        if (state < 0) {
            //在缓存中进行标记
            redisTemplate.opsForValue().set(COMMODITY_OVER+commoditySeckill.getCommodityId().toString(),
                                            true,10*60L, TimeUnit.SECONDS);
            return;
        }
        int orderId = commodityOrderService.seckillCommodityOrder(userId, commoditySeckill);
        if (orderId <= 0){
            return;
        }

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setCommodityId(commoditySeckill.getCommodityId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(userId);
        commoditySeckillMapper.commoditySeckillOrder(seckillOrder);
    }

    /**
     * -1 ：商品库存不足
     * 0 ：排队中，继续轮询
     * 秒杀成功，返回订单ID
     */
    @Override
    public int getSeckillResult(Integer commodityId, Integer userId) {

        SeckillOrder seckillOrder = commoditySeckillMapper.findSeckillOrder(userId, commodityId);
        if (seckillOrder!=null){
            return seckillOrder.getOrderId();
        }

        //查看商品是否被秒杀完
        Boolean isOver = redisTemplate.hasKey(COMMODITY_OVER + commodityId);
        if (isOver){
            return -1;
        }
        return 0;
    }
}
