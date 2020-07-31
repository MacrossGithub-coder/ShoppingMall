package org.macross.shopping_mall.service;

import org.macross.shopping_mall.model.entity.CommodityOrder;

import java.sql.SQLException;
import java.util.List;

public interface CommodityOrderService {
    int commodityOrder(Integer userId, Integer commodityId);

    List<CommodityOrder> findUserOrder(Integer userId);
}
