package com.miaoshaproject.error.emum;

import com.miaoshaproject.error.CommonError;

/**
 * @Auther: 范子祺
 * @Date: 2019/8/14 21:51
 * @Description:
 */
public enum EmBussinessError implements CommonError {

    //通用类型错误10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),

    //20000
    UNKNOW_CODE(20000,"未知错误"),


    //2000开头为用户信息定义相关错误
    USER_NOT_EXIST(20001,"用户不存在"),

    USER_LOGIN_FAIL(20002,"用户手机号或密码不正确"),

    USER_NOT_LOGIN(20003,"用户还未登录"),

    //商品信息3000
    STOCK_NOT_ENOUGH(30001,"库存不足")
    ;

    private EmBussinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {

        this.errMsg = errMsg;
        return this;
    }
}
