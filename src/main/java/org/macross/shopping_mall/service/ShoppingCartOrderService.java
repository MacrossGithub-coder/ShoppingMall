package org.macross.shopping_mall.service;

import org.macross.shopping_mall.model.entity.ShoppingCartOrder;

import java.util.List;

public interface ShoppingCartOrderService {
    int addToCart(Integer userId, Integer commodityId);

    int emptyCart(Integer userId);

    List<ShoppingCartOrder> findUserCartInfo(Integer userId);
}
