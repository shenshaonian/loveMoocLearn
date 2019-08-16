package com.miaoshaproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: 范子祺
 **/
public class ValidationResult {
    //校验结果是否有错
    private boolean hasErrors = false;

    //存放错误信息的map
    private Map<String,String> erroMsgMap = new HashMap<>();


    /**
     * 通过格式化字符串信息后去错误结果的msg方法
     * @return
     */
    public String getErrMsg(){
        return StringUtils.join(erroMsgMap.values().toArray(),",");
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErroMsgMap() {
        return erroMsgMap;
    }

    public void setErroMsgMap(Map<String, String> erroMsgMap) {
        this.erroMsgMap = erroMsgMap;
    }
}
