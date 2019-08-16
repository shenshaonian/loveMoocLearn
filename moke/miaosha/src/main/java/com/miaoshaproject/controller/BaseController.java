package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.emum.EmBussinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @description:
 * @author: 范子祺
 **/
public class BaseController {


    public static final String CONTENT_TYPE_FORMED_FORM = "application/x-www-form-urlencoded";

    public static final String CONTENT_TYPE_FORMED_JSON = "application/json";

    //定义exceptionHandle解决未被controller层吸收的Exception,这个处理好麻烦，，dp里面就简单很多啊
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommonReturnType handleException(HttpServletRequest request, Exception e){

//        CommonReturnType returnType = new CommonReturnType();
        HashMap<String, Object> responseData = new HashMap<>();
//        returnType.setStatus("fail");
        if (e instanceof BusinessException){
            BusinessException b = (BusinessException)e;

            responseData.put("errCode",b.getErrorCode());
            responseData.put("errMsg",b.getErrorMsg());
        } else {
            responseData.put("errCode", EmBussinessError.UNKNOW_CODE.getErrorCode());
            responseData.put("errMsg",EmBussinessError.UNKNOW_CODE.getErrorMsg());
        }
//        returnType.setData(responseData);
        return CommonReturnType.create("fail",responseData);
    }
}
