package org.macross.shopping_mall.mapper;

import org.apache.ibatis.annotations.Param;
import org.macross.shopping_mall.model.entity.CommodityOrder;

import java.util.List;

public interface CommodityOrderMapper {

    int commodityOrder(CommodityOrder commodityOrder);

    List<CommodityOrder> findUserOrder(@Param("user_id")Integer userId);
}
