package org.macross.shopping_mall.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 *         id	int	商品id
 *         category_id	int	商品类别id
 *         describe	varchar	商品描述
 *         home_img	varchar	首页展示图
 *         cover_img	varchar	封面图
 *         detail_img	varchar	细节图
 *         price	int	价格
 *         point	int	好评率,单位%
 *         create_time	datetime
 */


public class Commodity {

    private Integer id;

    @JsonProperty("category_id")
    private Integer categoryId;

    private String describe;

    @JsonProperty("home_img")
    private String homeImg;

    @JsonProperty("cover_img")
    private String coverImg;

    @JsonProperty("detail_img")
    private String detailImg;

    private Integer price;

    private Integer point;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTIme;

    private List<CommodityBanner> commodityBannerList;

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", describe='" + describe + '\'' +
                ", homeImg='" + homeImg + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", detailImg='" + detailImg + '\'' +
                ", price=" + price +
                ", point=" + point +
                ", createTIme=" + createTIme +
                ", commodityBannerList=" + commodityBannerList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getHomeImg() {
        return homeImg;
    }

    public void setHomeImg(String homeImg) {
        this.homeImg = homeImg;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Date getCreateTIme() {
        return createTIme;
    }

    public void setCreateTIme(Date createTIme) {
        this.createTIme = createTIme;
    }

    public List<CommodityBanner> getCommodityBannerList() {
        return commodityBannerList;
    }

    public void setCommodityBannerList(List<CommodityBanner> commodityBannerList) {
        this.commodityBannerList = commodityBannerList;
    }
}
