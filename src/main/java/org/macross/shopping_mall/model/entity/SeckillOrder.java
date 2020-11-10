package org.macross.shopping_mall.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CREATE TABLE `seckill_order`  (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `user_id` int(11) NULL DEFAULT NULL,
 *   `commodity_id` int(11) NULL DEFAULT NULL,
 *   `order_id` int(11) NULL DEFAULT NULL,
 *   PRIMARY KEY (`id`) USING BTREE
 * ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
 */
public class SeckillOrder {
    private Integer id;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("commodity_id")
    private Integer commodityId;

    @JsonProperty("order_id")
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "SeckillOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", commodityId=" + commodityId +
                ", orderId=" + orderId +
                '}';
    }
}
