package org.macross.shopping_mall.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * CREATE TABLE `commodity_seckill`  (
 *         `id` int(11) NOT NULL AUTO_INCREMENT,
 *         `commodity_id` int(11) NULL DEFAULT NULL,
 *         `seckill_price` int(255) NULL DEFAULT NULL,
 *         `stock` int(255) NULL DEFAULT NULL,
 *         `start_date` datetime(0) NULL DEFAULT NULL,
 *         `end_date` datetime(0) NULL DEFAULT NULL,
 *         PRIMARY KEY (`id`) USING BTREE
 *         ) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
 */
public class CommoditySeckill {

    private Integer id;

    @JsonProperty("commodity_id")
    private Integer commodityId;

    @JsonProperty("seckill_price")
    private Integer seckillPrice;

    private Integer stock;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

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

    public Integer getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Integer seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CommoditySeckill{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", seckillPrice=" + seckillPrice +
                ", stock=" + stock +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
