package org.macross.shopping_mall.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.macross.shopping_mall.mapper.CommoditySeckillMapper;
import org.macross.shopping_mall.model.entity.CommoditySeckill;
import org.macross.shopping_mall.model.entity.SeckillOrder;
import org.macross.shopping_mall.service.CommoditySeckillService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SeckillConsumer {

    @Autowired
    CommoditySeckillMapper commoditySeckillMapper;

    @Autowired
    CommoditySeckillService commoditySeckillService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            key = {"seckill"},
            exchange = @Exchange(name = "seckill_queue")))
    public void Consumer(String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SeckillMessage seckillMessage = objectMapper.readValue(message.getBytes(), SeckillMessage.class);
        SeckillOrder seckillOrder = commoditySeckillMapper.findSeckillOrder(seckillMessage.getUserId(), seckillMessage.getCommodityId());

        CommoditySeckill commoditySeckill = commoditySeckillMapper.findStockByCommodityId(seckillMessage.getCommodityId());
        if (commoditySeckill.getStock() < 0) {
            return;
        }

        //双重检测
        if (seckillOrder != null) {
            return;
        }
        commoditySeckillService.doSeckillService(seckillMessage.getUserId(),commoditySeckill);
    }
}
