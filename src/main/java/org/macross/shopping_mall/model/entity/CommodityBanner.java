package org.macross.shopping_mall.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
/**
 *         id	int
 *         commodity_id	int	商品id
 *         img	varchar	图片地址
 *         weight	int	数字越小排越前
 *         create_time	datetime
 */


public class CommodityBanner {

    private Integer id;

    private Integer commodityId;

    private String img;

    private Integer weight;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Override
    public String toString() {
        return "CommodityBanner{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", img='" + img + '\'' +
                ", weight=" + weight +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
