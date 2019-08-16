package com.miaoshaproject.service.userModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @description:  商品的信息模型
 * @author: 范子祺
 **/
public class ItemModel {

    //商品编号
    private Integer id;

    //商品名称
    @NotBlank(message = "需要填商品名称")
    private String title;

    //商品价格
    @NotNull(message = "需要填商品价格")
    @Min(value = 0,message = "商品价格需要大于0")
    private BigDecimal price;

    //商品的库存
    @NotNull(message = "需要填商品库存")
    private Integer stock;

    //商品描述
    @NotBlank(message = "需要填商品名称")
    private String decription;

    //销量
    private Integer sales;

    //商品描述图片的url
    @NotBlank(message = "需要填图片的url")
    private String imgUrl;


    //聚合模型 --如果promoMedel不为空，则表示其拥有还未结束的秒杀活动
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", decription='" + decription + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
