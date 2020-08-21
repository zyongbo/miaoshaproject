package com.miaoshaproject.service.impl;

import com.miaoshaproject.converter.DataModelToDataObjectConverter;
import com.miaoshaproject.dao.dataobject.UserDO;
import com.miaoshaproject.dao.dataobject.UserPasswordDO;
import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.converter.DataObjectToDataModelConverter;
import com.miaoshaproject.error.BusinessErrorEnum;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.model.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private DataObjectToDataModelConverter dataObjectToDataModelConverter;

    @Autowired
    private DataModelToDataObjectConverter dataModelToDataObjectConverter;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }

        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

        return dataObjectToDataModelConverter.convertToUserModelFromUserPasswordDO(userDO, userPasswordDO);
    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
//        if (StringUtils.isEmpty(userModel.getName())
//            || userModel.getGender() == null
//            || userModel.getAge() == null
//            || StringUtils.isEmpty(userModel.getTelephone())) {
//            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR);
//        }

        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMsg());
        }

        UserDO userDO = dataModelToDataObjectConverter.convertToUserDOFromUserModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "手机号已被注册");
        }

        userModel.setId(userDO.getId());
        if (userModel.getId() == null || userModel.getId() <= 0
            || userModel.getEncryptPassword() == null) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
        UserPasswordDO userPasswordDO = dataModelToDataObjectConverter.convertToUserPasswordDOFromUserModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserModel validateLogin(String telephone, String encryptedPassword) throws BusinessException {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null) {
            throw new BusinessException(BusinessErrorEnum.USER_LOGIN_FAIL);
        }

        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = dataObjectToDataModelConverter.convertToUserModelFromUserPasswordDO(userDO, userPasswordDO);

        if (!StringUtils.equals(encryptedPassword, userModel.getEncryptPassword())) {
            throw new BusinessException(BusinessErrorEnum.USER_LOGIN_FAIL);
        }
        return userModel;
    }
}
