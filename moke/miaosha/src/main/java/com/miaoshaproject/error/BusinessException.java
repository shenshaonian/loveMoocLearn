package com.miaoshaproject.error;

/**
 * @description:
 * @author: 范子祺
 **/

//包装器的业务实现
public class BusinessException extends Exception implements CommonError{

    private CommonError commonError;

    //直接接收EmBussinessError的传参，，用于构建业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    //自定义errormsg
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errMsg);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.commonError.setErrorMsg(errMsg);
        return this;
    }
}
