package com.miaoshaproject.service.userModel;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @description: 秒杀商品
 * @author: 范子祺
 **/
public class PromoModel {


    //todo   为了简单起见---同一个活动只能适用一个itemid
    /**
     * 自增中间
     */
    private Integer id;

    /**
     * 秒杀活动状态 1还未开始  2进行中 3已结束
     */
    private Integer status;


    /**
     * 秒杀活动名称
     */
        private String promoName;

    /**
     * 活动开始时间
     */
    private DateTime startDate;


    /**
     * 秒杀结束时间
     */
    private DateTime endDate;

    /**
     * 活动适用商品--这个应该对应promoId？？？？
     */
    private Integer itemId;

    /**
     * 秒杀活动的商品价格
     */
    private BigDecimal promoItemPrice;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }
}
