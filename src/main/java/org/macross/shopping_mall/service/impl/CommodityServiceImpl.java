package org.macross.shopping_mall.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.macross.shopping_mall.config.CacheKeyManager;
import org.macross.shopping_mall.mapper.CommodityMapper;
import org.macross.shopping_mall.model.entity.Commodity;
import org.macross.shopping_mall.model.entity.CommodityCategory;
import org.macross.shopping_mall.model.entity.HomeBanner;
import org.macross.shopping_mall.service.CommodityService;
import org.macross.shopping_mall.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    BaseCache baseCache;

    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public List<HomeBanner> homeBanner() {

        try {
            Object Cache = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_LIST_KEY, () ->
            {
                return commodityMapper.homeBanner();
            });
            if (Cache instanceof List){
                return (List<HomeBanner>)Cache;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<CommodityCategory> homeCommodity() {

        try {
            Object Cache = baseCache.getOneHourCache().get(CacheKeyManager.INDEX_COMMODITY_LIST_KEY, () ->
            {
                return commodityMapper.homeCommodity();
            });
            if (Cache instanceof List){
                return (List<CommodityCategory>)Cache;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Commodity findCommodityDetail(Integer commodityId) {

        String INDEX_COMMODITY_DETAIL_KEY = String.format(CacheKeyManager.INDEX_COMMODITY_DETAIL_KEY, commodityId);

        try {

            Object Cache =  baseCache.getOneHourCache().get(INDEX_COMMODITY_DETAIL_KEY,() ->
            {
                return commodityMapper.findCommodityDetail(commodityId);
            });
            if (Cache instanceof Commodity){
                return (Commodity)Cache;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
