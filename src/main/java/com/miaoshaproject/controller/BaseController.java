package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessErrorEnum;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.controller.response.CommonReturnType;
import com.miaoshaproject.controller.response.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    // define exception handler to resolve the exceptions thrown by controller
    // all the controller will have the same way to handle exception thrown by controller level
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception exception) {
        Map<String, Object> responseData = new HashMap<>();
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            responseData.put("errorCode", businessException.getErrorCode());
            responseData.put("errorMsg", businessException.getErrorMsg());
        } else {
            responseData.put("errorCode", BusinessErrorEnum.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errorMsg", BusinessErrorEnum.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnType.create(ResponseStatusEnum.FAIL, responseData);
    }
}
