package com.miaoshaproject.response;

/**
 * @description:
 * @author: 范子祺
 **/
public class CommonReturnType {
    //success fail
    private String status;

    private Object data;

    public static CommonReturnType create(Object object){
        return CommonReturnType.create("success",object);
    }

    public static CommonReturnType create(String status,Object object){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(object);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonReturnType{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
