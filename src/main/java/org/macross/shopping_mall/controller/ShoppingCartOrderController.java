package org.macross.shopping_mall.controller;


import org.macross.shopping_mall.model.entity.ShoppingCartOrder;
import org.macross.shopping_mall.service.ShoppingCartOrderService;
import org.macross.shopping_mall.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/cart")
public class ShoppingCartOrderController {

    @Autowired
    ShoppingCartOrderService shoppingCartOrderService;

    @RequestMapping("add_to_cart")
    public JsonData addToCart(@RequestParam("commodity_id")Integer commodityId, HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");
        int result =  shoppingCartOrderService.addToCart(userId,commodityId);

        return result >0 ? JsonData.buildSuccess("加入购物车成功"):JsonData.buildError("加入购物车失败");
    }
    @RequestMapping("empty_cart")
    public  JsonData emptyCart(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");
        int result = shoppingCartOrderService.emptyCart(userId);

        return result >0 ? JsonData.buildSuccess("清空购物车成功"):JsonData.buildError("清空购物车失败");
    }

    @RequestMapping("find_user_cart_info")
    public JsonData findUserCartInfo(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");
        List<ShoppingCartOrder> shoppingCartOrderList = shoppingCartOrderService.findUserCartInfo(userId);
        return shoppingCartOrderList.size() >0 ? JsonData.buildSuccess(shoppingCartOrderList):JsonData.buildError("该用户购物车为空");
    }

}
