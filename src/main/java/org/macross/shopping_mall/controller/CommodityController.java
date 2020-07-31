package org.macross.shopping_mall.controller;

import org.macross.shopping_mall.model.entity.Commodity;
import org.macross.shopping_mall.model.entity.CommodityCategory;
import org.macross.shopping_mall.model.entity.HomeBanner;
import org.macross.shopping_mall.service.CommodityService;
import org.macross.shopping_mall.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/list")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @GetMapping("home_banner")
    public JsonData homeBanner(){
        List<HomeBanner> homeBannerList = commodityService.homeBanner();
        return homeBannerList != null ? JsonData.buildSuccess(homeBannerList):JsonData.buildError("查询失败");
    }

    @GetMapping("home_Commodity")
    public JsonData home_Commodity(){

       List<CommodityCategory> commodityCategoryList  = commodityService.homeCommodity();
       return commodityCategoryList != null ? JsonData.buildSuccess(commodityCategoryList):JsonData.buildError("查询失败");
    }

    @GetMapping("find_commodity_detail")
    public JsonData findCommodityDetail(@RequestParam("commodity_id")Integer commodityId){

        Commodity commodity =  commodityService.findCommodityDetail(commodityId);
        return commodity != null ? JsonData.buildSuccess(commodity):JsonData.buildError("查询失败");
    }
}
