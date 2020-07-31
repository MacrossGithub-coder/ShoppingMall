package org.macross.shopping_mall.service;

import org.macross.shopping_mall.model.entity.Commodity;
import org.macross.shopping_mall.model.entity.CommodityCategory;
import org.macross.shopping_mall.model.entity.HomeBanner;

import java.util.List;

public interface CommodityService {

    List<HomeBanner> homeBanner();

    List<CommodityCategory> homeCommodity();

    Commodity findCommodityDetail(Integer commodityId);
}
