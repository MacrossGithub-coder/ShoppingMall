package org.macross.shopping_mall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.annotations.Param;
import org.macross.shopping_mall.service.CommoditySeckillService;
import org.macross.shopping_mall.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/pri/seckill")
public class CommoditySeckillController {

    @Autowired
    CommoditySeckillService commoditySeckillService;

    /**
     * -1 ：库存不足秒杀失败
     * -2 : 该用户存在重复下单行为
     *  0 ：排队中，继续轮询
     */
    @RequestMapping("commodity_seckill")
    public JsonData commoditySeckill(@RequestParam("commodity_id") Integer commodityId, HttpServletRequest request) throws JsonProcessingException {

        Integer userId = (Integer) request.getAttribute("user_id");

        int result = commoditySeckillService.doCommoditySeckill(commodityId, userId);

        return result == 0 ? JsonData.buildSuccess("排队中……") :
                (result == -1 ? JsonData.buildError(-1, "库存不足秒杀失败") : JsonData.buildError(-2, "该用户存在重复下单行为"));
    }

    /**
     * -1 ：商品库存不足
     * 0 ：排队中，继续轮询
     * 秒杀成功，返回订单ID
     */
    @RequestMapping("get_seckill_result")
    public JsonData getSeckillResult(@RequestParam("commodity_id") Integer commodityId, HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        int result = commoditySeckillService.getSeckillResult(commodityId,userId);
        return result >0 ? JsonData.buildSuccess("商品秒杀成功，订单号为："+result) :
                (result == -1 ? JsonData.buildError(-1, "商品库存不足秒杀失败") : JsonData.buildError(0, "订单尚未处理完"));
    }


}
