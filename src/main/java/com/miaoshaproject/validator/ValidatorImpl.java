package com.miaoshaproject.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object bean) {
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0) {
            result.setHasErrors(true);
            constraintViolationSet.forEach(violation -> {
                String errorMsg = violation.getMessage();
                String propertyName = violation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName, errorMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // instantiate hibernate validator through validator factory
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
