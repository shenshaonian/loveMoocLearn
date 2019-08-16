package com.miaoshaproject.service.userModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: service
 * @author: 范子祺
 **/
public class UserModel {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    //一定要知道自己在些什么，，，，写什么  不然老是写错地方这种无聊错误
    private Byte gender;


    @NotNull(message = "年龄需要填写")
    @Min(value = 0,message = "年龄为自然数")
    @Max(value = 150,message = "年龄到150的很稀少还能玩手机，目前")
    private Integer age;
    @NotBlank(message = "手机号需要填写")
    private String telphone;
    private String registerMode;
    private String thirdPartyId;
    @NotBlank(message = "密码需要填写")
    private String encrptPassword;

    //临时加用一下
    private String otpCode;

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }


//    public Integer getGender() {
//        return gender;
//    }
//
//    public void setGender(Integer gender) {
//        this.gender = gender;
//    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }
}
