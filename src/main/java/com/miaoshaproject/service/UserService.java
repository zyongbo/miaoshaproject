package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.model.UserModel;

public interface UserService {
    public UserModel getUserById(Integer id);
    public void register(UserModel userModel) throws BusinessException;
    public UserModel validateLogin(String telephone, String encryptedPassword) throws BusinessException;
}
