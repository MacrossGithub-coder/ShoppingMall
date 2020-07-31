package org.macross.shopping_mall.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * id	int	类别id
 * describe	varchar	描述
 * create_time	datetime
 */
public class CommodityCategory {

    private Integer id;

    private String describe;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private List<Commodity> commodityList;

    @Override
    public String toString() {
        return "CommodityCategory{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                ", createTime=" + createTime +
                ", commodityList=" + commodityList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }
}
