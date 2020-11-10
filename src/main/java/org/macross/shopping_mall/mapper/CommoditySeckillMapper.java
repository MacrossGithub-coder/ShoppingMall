package org.macross.shopping_mall.mapper;

import org.apache.ibatis.annotations.Param;
import org.macross.shopping_mall.model.entity.CommoditySeckill;
import org.macross.shopping_mall.model.entity.SeckillOrder;

public interface CommoditySeckillMapper {
    SeckillOrder findSeckillOrder(@Param("user_id")Integer userId,@Param("commodity_id") Integer commodityId);

    CommoditySeckill findStockByCommodityId(@Param("commodity_id")Integer commodityId);

    int reduceStock(@Param("commodity_id")Integer commodityId);

    int commoditySeckillOrder(SeckillOrder seckillOrder);
}
