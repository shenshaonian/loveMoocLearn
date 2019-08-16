package com.miaoshaproject.service;

import com.miaoshaproject.service.userModel.PromoModel;

/**
 * @description: 秒杀活动操作
 * @author: 范子祺
 */
public interface IPromoService {

    /**
     * 获得秒杀商品信息---给前端用来展示
     * @param itemId
     * @return
     */
    PromoModel getPromoByItemId(Integer itemId);
}

