package org.macross.shopping_mall.controller;

import org.macross.shopping_mall.model.entity.CommodityOrder;
import org.macross.shopping_mall.service.CommodityOrderService;
import org.macross.shopping_mall.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
public class CommodityOrderController {

    @Autowired
    CommodityOrderService commodityOrderService;

    /**
     * @param commodityId
     * @param request
     * @return
     * 返回JsonData
     * code: -1 数据库用户信息有误
     * code: -2 数据库商品信息有误
     * code: -3 余额不足
     * code: 0 正常
     */
    @RequestMapping("commodity_order")
    public JsonData commodityOrder(@RequestParam("commodity_id")Integer commodityId, HttpServletRequest request)  {

        Integer userId = (Integer)request.getAttribute("user_id");

        int result = commodityOrderService.commodityOrder(userId,commodityId);

        return result > 0 ? JsonData.buildSuccess("下单成功"):JsonData.buildError("下单失败");

    }

    @RequestMapping("find_user_order")
    public JsonData findUserOrder(HttpServletRequest request){

        Integer userId = (Integer)request.getAttribute("user_id");

        List<CommodityOrder> commodityOrderList = commodityOrderService.findUserOrder(userId);

        return commodityOrderList.size()>0 ? JsonData.buildSuccess(commodityOrderList):JsonData.buildError("该用户未下单");
    }
}
