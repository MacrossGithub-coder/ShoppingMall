package org.macross.shopping_mall.controller;

import org.macross.shopping_mall.service.ElasticSearchService;
import org.macross.shopping_mall.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pub/es")
public class ElasticSearchController {

    @Autowired
    ElasticSearchService elasticSearchService;

    @GetMapping("update_data")
    public JsonData updateData() throws IOException {
        boolean result = elasticSearchService.updateData();
        return !result ? JsonData.buildSuccess("Update true"):JsonData.buildError("Update False");
    }

    @RequestMapping("search_commodity")
    public JsonData searchCommodity(@RequestParam("keyword")String keyword) throws IOException {
        List<Map<String,Object>> resultList =  elasticSearchService.searchCommodity(keyword);
        return resultList.size()>0 ? JsonData.buildSuccess(resultList):JsonData.buildError("未查询到结果");
    }
}
