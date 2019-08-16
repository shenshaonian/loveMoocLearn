package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewObject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.emum.EmBussinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.IUserService;
import com.miaoshaproject.service.userModel.UserModel;
import com.miaoshaproject.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

/**
 * @description: 用户注册，登录
 * @author: 范子祺
 **/
@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController{
    @Autowired
    IUserService userService;

    @Autowired  //通过springbean管理的这个，实际上是一个代理的servlet，并不是说这个成单例模式了
    private HttpServletRequest httpServletRequest;



    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException{
        UserModel userById = userService.getUserById(id);
        if (null == userById){
            throw new BusinessException(EmBussinessError.USER_NOT_EXIST);
//            userById.setEncrptPassword("123");
        }

        UserVO userVO = convertFromModel(userById);
        CommonReturnType returnType = CommonReturnType.create(userVO);


        return returnType;
    }

    /**
     * 用户获取短信
     * @param telphone
     * @return
     */
    @RequestMapping(value = "/getOtp",method = RequestMethod.GET, consumes = CONTENT_TYPE_FORMED_JSON)
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone")String telphone){
        //按照一定规则生成验证码
        Random random = new Random();
        int i = random.nextInt(99999);
        i+=10000;
        String optCode = String.valueOf(i);

        //将otp验证码同用户对应的手机号关联,像公司就用的redis管理的手机号和短信验证码的对应，同时还要和数据库关联。
        //这里使用httpSession的方式，绑定手机号和短信验证码的关联
        httpServletRequest.getSession().setAttribute(telphone,optCode);

        //将otp验证码通过短信通道发送给用户，省略
        System.out.println("telphone:"+telphone+" & otpCode:"+optCode);

        return CommonReturnType.create(null);

    }


    /**
     * 用户注册接口
     * @param telphone
     * @param otpCode
     * @param name
     * @param gender
     * @param age
     * @return
     */

    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = CONTENT_TYPE_FORMED_FORM)
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone")String telphone,
                                     @RequestParam(name = "otpCode")String otpCode,
                                     @RequestParam(name = "name")String name,
                                     @RequestParam(name = "gender")Integer gender,
                                     @RequestParam(name = "age")Integer age,
                                     @RequestParam(name = "password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println("?????");
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!Objects.equals(inSessionOtpCode,otpCode)){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"短信验证码错误");
        }

        //getset   恶心。。。。
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender((byte) gender.intValue());
//        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
//        new Byte(String.valueOf(gender.intValue()))
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        //还是要研究一下这些加密 的
//        userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));
        userModel.setEncrptPassword(this.EncodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

//    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = CONTENT_TYPE_FORMED_JSON)
//    @ResponseBody
//    public CommonReturnType register(@RequestBody UserModel userModel) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
//        System.out.println("?????");
//        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(userModel.getTelphone());
//        if (!Objects.equals(inSessionOtpCode,userModel.getOtpCode())){
//            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"短信验证码错误");
//        }
//
//        //getset   恶心。。。。
////        UserModel userModel = new UserModel();
////        userModel.setName(name);
////        userModel.setGender((byte) gender.intValue());
////        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
////        new Byte(String.valueOf(gender.intValue()))
////        userModel.setAge(age);
////        userModel.setTelphone(telphone);
//        userModel.setRegisterMode("byphone");
//        //还是要研究一下这些加密 的
////        userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));
//        userModel.setEncrptPassword(this.EncodeByMd5(userModel.getEncrptPassword()));
//        userService.register(userModel);
//        return CommonReturnType.create(null);
//    }


    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = CONTENT_TYPE_FORMED_FORM)
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="telphone") String telphone,
                                  @RequestParam(name="password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"传入数据不能为空");
        }

        //用户登录服务，校验入参和数据库中 是否一样
        UserModel userModel = userService.validateLogin(telphone,this.EncodeByMd5(password));

        //将用户登录凭证假如到用户登录成功的session内   一般来说扔到缓存中去的
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);
    }

    /**
     * 这里为啥要这样做还是要去研究一下
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    private UserVO convertFromModel(UserModel userModel){
        if (null == userModel){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }






}
