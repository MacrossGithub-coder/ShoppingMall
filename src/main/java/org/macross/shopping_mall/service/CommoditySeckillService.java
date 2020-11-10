package org.macross.shopping_mall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.macross.shopping_mall.model.entity.CommoditySeckill;

public interface CommoditySeckillService {
    int doCommoditySeckill(Integer commodityId, Integer userId) throws JsonProcessingException;

    void doSeckillService(Integer userId, CommoditySeckill commoditySeckill);

    int getSeckillResult(Integer commodityId, Integer userId);
}
