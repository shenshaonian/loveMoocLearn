package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.userModel.OrderModel;

/**
 * @description: 下单操作
 * @author: 范子祺
 */
public interface IOrderService {
    /**
     * 1前端url上传过来秒杀活动id，  然后下单接口校验对应id是否属于对应商品，以及活动
     * 是否开始
     * 2直接在下单接口内判断对应的商品是否存在，存在进行中，则直接以秒杀价格下单
     *
     * 第二种方案会造成---订单领域模型和秒杀领域的耦合，就是我们还要去秒杀那边查一遍。
     * 但是用第一种，前端传了，我们就可以极大的减少许多不必要的查询。
     * @param userId  用户id
     * @param itemId    商品id
     * @param amount    总数
     * @param promoId   这个对应？？？---这个就是秒杀表中商品的主键信息
     * @return
     * @throws BusinessException
     */
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId, Integer amount) throws BusinessException;

}

