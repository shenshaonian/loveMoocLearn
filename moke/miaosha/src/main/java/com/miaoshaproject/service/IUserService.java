package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.userModel.UserModel;

/**
 * @description:
 * @author: 范子祺
 */
public interface IUserService {

    public UserModel getUserById(Integer id);

    /**
     * 用户注册
     * @param userModel
     */
    void register(UserModel userModel) throws BusinessException;

    /**
     * 校验登录
     * @param telphone
     * @param encrptPassword
     */
    UserModel validateLogin(String telphone,String encrptPassword) throws BusinessException;
}