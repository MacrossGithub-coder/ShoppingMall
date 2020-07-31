package org.macross.shopping_mall.mapper;

import org.apache.ibatis.annotations.Param;
import org.macross.shopping_mall.model.entity.ShoppingCartOrder;

import java.util.List;

public interface ShoppingCartOrderMapper {

    int addToCart(ShoppingCartOrder shoppingCartOrder);

    List<ShoppingCartOrder> findUserCartInfo(@Param("user_id")Integer userId);

    int updateOrderState(@Param("user_id")Integer userId);
}
