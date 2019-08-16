package com.miaoshaproject.service.userModel;

import java.math.BigDecimal;

/**
 * @description: 用户下单交易模型
 * @author: 范子祺
 **/
public class OrderModel {
    /**
     * 交易流水号
     */
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer itemId;

    /**
     * 若非空，则表示已秒杀商品方式下单
     */
    private Integer promoId;

    /**
     * 若promoId非空，则表示秒杀商品价格
     * 购买商品的单价，这个要求以前买的和现在价格不一样，，但是你以前的单价查出来不能变，
     */
    private BigDecimal itemPrice;

    /**
     * 购买数量
     */
    private Integer amount;

    /**
     * 若promoId非空，则表示秒杀商品总价
     * 购买总金额
     */
    private BigDecimal orderPrice;


    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
