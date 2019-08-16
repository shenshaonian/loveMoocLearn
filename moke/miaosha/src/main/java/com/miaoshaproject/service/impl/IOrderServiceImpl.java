package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderDOMapper;
import com.miaoshaproject.dao.SequenceDOMapper;
import com.miaoshaproject.dataObject.OrderDO;
import com.miaoshaproject.dataObject.SequenceDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.emum.EmBussinessError;
import com.miaoshaproject.service.IUserService;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.IOrderService;
import com.miaoshaproject.service.userModel.ItemModel;
import com.miaoshaproject.service.userModel.OrderModel;
import com.miaoshaproject.service.userModel.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:下单操作
 * @author: 范子祺
 **/
@Service
public class IOrderServiceImpl implements IOrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    /**
     * 获得订单自增序列号
     */
    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException {
        //下单商品是否存在。用户是否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if (null == itemModel){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }

        UserModel userModel = userService.getUserById(userId);
        if (null == userModel){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
        }
        if (amount <=0 || amount>99){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"购买数量溢出");
        }

//        秒杀活动状态 1还未开始  2进行中 3已结束
        //校验活动信息  视屏中itemModel.getPromoModel().getStatus()这里敲错了？？？
        if (null != promoId){
          if (promoId.intValue() != itemModel.getPromoModel().getId()){
              throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"活动信息不存在");
          }else if (itemModel.getPromoModel().getStatus().intValue()!=2){
              throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"活动还未开始");
          }
        }

        //落单减库存，，，，支付减库存，，， 落单减库存听他说的就是靠谱些
        boolean b = itemService.decreaseStock(itemId, amount);
        if (!b){
            throw new BusinessException(EmBussinessError.STOCK_NOT_ENOUGH);
        }

        //订单入库  这个con层就应该用实体接收
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if (promoId != null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }


        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));

        //生成交易订单号------要解决订单下单失败，回滚的事物全局唯一性，失败了也是一个失败的订单，下次
        //就不能用这个订单号了
        String orderNo = generateOrderNo();
        orderModel.setId(orderNo);

        OrderDO orderDO = convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);
        //返回前端

        //todo   销量没有做增加---如下
        itemService.increaseSales(itemId,amount);



        return orderModel;
    }


    /**
     * 交易模型  转成  数据库插入 模型
     * @param orderModel
     * @return
     */
    private OrderDO convertFromOrderModel(OrderModel orderModel){
        if (null == orderModel){
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        return orderDO;
    }

    //test
    public static void main(String[] args){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ISO_DATE).replace("-",""));
    }

    /**
     * 生成订单号
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo(){
        //订单号16位
        StringBuilder stringBuilder = new StringBuilder();
        //前8位为时间信息 年月日-----可以用于归档信息
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);
        //中间6位为自增序列----防止重复---去建一个通用sql表
        //获取当前sequence
        int sequence = 0;

        //获取当前sequence
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue()+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6-sequenceStr.length();i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);


        //最后2位为分库，分表位----为以后
//        Integer userId = 1000122;
//        userId % 100

        //最后两位暂时写死
        stringBuilder.append("00");
        return stringBuilder.toString();
    }
}
