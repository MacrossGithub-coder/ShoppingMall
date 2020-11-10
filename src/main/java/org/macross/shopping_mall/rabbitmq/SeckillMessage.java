package org.macross.shopping_mall.rabbitmq;

public class SeckillMessage {

    private Integer userId;

    private Integer commodityId;

    public SeckillMessage(){

    }
    public SeckillMessage(Integer userId,Integer commodityId){
        this.userId = userId;
        this.commodityId = commodityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public String toString() {
        return "SeckillMessage{" +
                "userId=" + userId +
                ", CommodityId=" + commodityId +
                '}';
    }
}
