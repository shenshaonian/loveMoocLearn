package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @description:
 * @author: 范子祺
 **/
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;


    public ValidationResult validate(Object bean){
        ValidationResult result = new ValidationResult();

        //如果 传入的bean  定义的emm规则 有违背 了这个方法，那么返回结果里面就会有值
        Set<ConstraintViolation<Object>> validateSet = validator.validate(bean);
        if (validateSet.size()>0){
            //有错误
            result.setHasErrors(true);
            validateSet.forEach(validate->{
                //错误信息
                String errMsg = validate.getMessage();
                //哪个字段错了，
                String propertyName = validate.getPropertyPath().toString();
                result.getErroMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }

    /**
     * 这个方法会在ValidatorImpl实例化bean之后被回调
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂的初始化 方式 实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
