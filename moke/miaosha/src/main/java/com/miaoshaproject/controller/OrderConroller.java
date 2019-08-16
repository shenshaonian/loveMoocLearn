package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.emum.EmBussinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.IOrderService;
import com.miaoshaproject.service.userModel.OrderModel;
import com.miaoshaproject.service.userModel.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: 范子祺
 **/
@Controller
@RequestMapping("/order")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class OrderConroller extends BaseController{

    @Autowired
    private IOrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 下单请求
     * @param itemId    商品id
     * @param promoId   商品秒杀表的主键
     * @param amount    购买数量
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/creatorder",method = RequestMethod.POST,consumes = CONTENT_TYPE_FORMED_FORM)
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,
                                        @RequestParam(name = "promoId",required = false)Integer promoId,
                                        @RequestParam(name = "amount")Integer amount) throws BusinessException {

        Boolean isLogin = (Boolean)httpServletRequest.getSession().getAttribute("IS_LOGIN");

        if (null == isLogin || !isLogin.booleanValue()){
            throw new BusinessException(EmBussinessError.USER_NOT_LOGIN,"请登录后下单");
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        //获取用户的登录信息
        OrderModel orderModel = orderService.createOrder(userModel.getId(),itemId,promoId,amount);
        return CommonReturnType.create(null);
    }


}
