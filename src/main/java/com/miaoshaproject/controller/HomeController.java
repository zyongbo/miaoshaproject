package com.miaoshaproject.controller;

import com.miaoshaproject.controller.response.CommonReturnType;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.converter.DataModelToViewObjectConverter;
import com.miaoshaproject.error.BusinessErrorEnum;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.HomeService;
import com.miaoshaproject.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private DataModelToViewObjectConverter dataModelToViewObjectConverter;

    @RequestMapping("/")
    public CommonReturnType home() throws BusinessException {

        // this should not directly get UserDO, suppose to get UserVO
        // UserDO should only be used in service/business logic level
        // here it is only a hello world example
        UserModel userModel = homeService.getUserById(1);
        if (userModel == null) {
            throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
        }

        UserVO userVO = dataModelToViewObjectConverter.convertToUserVOFromUserModel(userModel);

        return CommonReturnType.create(userVO);
    }
}
