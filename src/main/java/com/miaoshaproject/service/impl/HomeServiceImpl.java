package com.miaoshaproject.service.impl;

import com.miaoshaproject.converter.DataObjectToDataModelConverter;
import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dao.dataobject.UserDO;
import com.miaoshaproject.dao.dataobject.UserPasswordDO;
import com.miaoshaproject.service.HomeService;
import com.miaoshaproject.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private DataObjectToDataModelConverter dataObjectToDataModelConverter;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }

        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

        return dataObjectToDataModelConverter.convertToUserModelFromUserPasswordDO(userDO, userPasswordDO);
    }
}
