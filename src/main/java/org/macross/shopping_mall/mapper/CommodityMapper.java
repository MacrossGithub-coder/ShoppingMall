package org.macross.shopping_mall.mapper;

import org.apache.ibatis.annotations.Param;
import org.macross.shopping_mall.model.entity.Commodity;
import org.macross.shopping_mall.model.entity.CommodityCategory;
import org.macross.shopping_mall.model.entity.HomeBanner;

import java.util.List;

public interface CommodityMapper {

    List<HomeBanner> homeBanner();

    List<CommodityCategory> homeCommodity();

    List<Commodity> findAllCommodity();

    Commodity findCommodityDetail(@Param("commodity_id") Integer commodityId);
}
