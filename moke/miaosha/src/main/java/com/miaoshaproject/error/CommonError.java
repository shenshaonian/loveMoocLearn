package com.miaoshaproject.error;

/**
 * @description:
 * @author: 范子祺
 */
public interface CommonError {
    public int getErrorCode();

    public String getErrorMsg();

    public CommonError setErrorMsg(String errMsg);

}

