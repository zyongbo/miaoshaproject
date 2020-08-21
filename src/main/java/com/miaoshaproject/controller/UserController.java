package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.converter.DataModelToViewObjectConverter;
import com.miaoshaproject.error.BusinessErrorEnum;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.controller.response.CommonReturnType;
import com.miaoshaproject.security.Encoder;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private DataModelToViewObjectConverter dataModelToViewObjectConverter;

    @Autowired
    private Encoder encoder;

    // spring boot can guarantee that the auto injected httpServletRequest is the request in current context
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone") String telephone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "电话或者密码不能为空");
        }

        UserModel userModel = userService.validateLogin(telephone, encoder.encodeByMd5(password));

        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") String age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(telephone);
        if (!StringUtils.equals(inSessionOtpCode, otpCode)) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "短信验证码不符合");
        }

        UserModel userModel = new UserModel.UserModelBuilder()
                                .setName(name)
                                .setAge(Integer.valueOf(age))
                                .setGender(gender)
                                .setTelephone(telephone)
                                .setRegisterMode("byphone")
                                .build();

        userModel.setEncryptPassword(encoder.encodeByMd5(password));

        userService.register(userModel);

        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone) {
        Random random = new Random();
        int otp = random.nextInt(99999);
        otp += 10000;
        String otpCode = String.valueOf(otp);

        // put OTP code and corresponding telephone in the session or redis
        httpServletRequest.getSession().setAttribute(telephone, otpCode);

        // use third party software to send the otp code to user
        System.out.println("telephone = " + telephone + " & otpCode = " + otpCode);

        return CommonReturnType.create(null);
    }

    // request path: /user/get
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
        }

        UserVO userVO = dataModelToViewObjectConverter.convertToUserVOFromUserModel(userModel);

        return CommonReturnType.create(userVO);
    }
}
