package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dataObject.UserDO;
import com.miaoshaproject.dataObject.UserPasswordDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.emum.EmBussinessError;
import com.miaoshaproject.service.IUserService;
import com.miaoshaproject.service.userModel.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * @description:
 * @author: 范子祺
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null){
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
        UserModel userModel = convertFromDataObject(userDO,userPasswordDO);
        return userModel;
    }

    /**
     * 用户注册
     * @param userModel
     */
    //后面两个插入表，要保证为一个事物
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException{
        if (userModel == null){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }



        //它这代码是真恶心，抄完了我自己用spring+mybatis写一版看看
//        if (StringUtils.isEmpty(userModel.getName())
//                ||userModel.getGender() == null || userModel.getAge()==null
//        ||StringUtils.isEmpty(userModel.getTelphone())){
//            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
//        }

        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }


        UserDO userDO = convertFromModel(userModel);
        try {
            int i = userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"手机号已注册，请联系范hunzi");
        }
//        System.out.println(i+"insertuser");

        userModel.setId(userDO.getId());

        //存密码实体
        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        int j = userPasswordDOMapper.insertSelective(userPasswordDO);//还没加密
        System.out.println(j+"insertpassword");
        return;

    }

    /**  这个人直接把用户信息和密码表弄个一张表。。。。就行了，不就是还要加密码，服了
     * 校验登录
     * @param telphone
     * @param encrptPassword
     */
    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException{
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if (userDO==null){
            throw new BusinessException(EmBussinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);

        if(!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())){
            throw new BusinessException(EmBussinessError.USER_LOGIN_FAIL);
        }

        return userModel;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if (userModel==null){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());//这个id也是醉了
        return userPasswordDO;
    }

    /**
     * 将前端传来用于注册的实体转换成数据库对应的
     * @param userModel
     * @return
     */
    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null){
            return null;
        }
        UserDO userDO = new UserDO();
        //这种方法要少用，它耗费性能吧，，大佬说的，还没研究，项目里最好getset一个个搞进去
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    /**
     * 这个是加密
     * @param userDO
     * @param userPasswordDO
     * @return
     */
    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (null==userDO){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if (null!=userPasswordDO) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
