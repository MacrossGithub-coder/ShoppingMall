package org.macross.shopping_mall.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
     id	int
     out_trade_no	varchar	订单唯一标识
     user_id	int	用户id
     state	int	0表示未支付，1表示已支付
     total_fee	int	支付金额，单位分
     commodity_id	int	商品id
     commodity_describe	varchar	商品描述
     commodity_img	varchar	商品封面图
     address    varchar 配送地址
     create_time	datetime	订单生成时间

 */

public class CommodityOrder {

    private Integer id;

    @JsonProperty("out_trade_no")
    private String outTradeNo;

    @JsonProperty("user_id")
    private Integer userId;

    private Integer state;

    @JsonProperty("total_fee")
    private Integer totalFee;

    @JsonProperty("commodity_id")
    private Integer commodityId;

    @JsonProperty("commodity_describe")
    private String commodityDescribe;

    private String address;

    @JsonProperty("commodity_img")
    private String commodityImg;


    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityDescribe() {
        return commodityDescribe;
    }

    public void setCommodityDescribe(String commodityDescribe) {
        this.commodityDescribe = commodityDescribe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
