package com.miaoshaproject.converter;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.model.ItemModel;
import com.miaoshaproject.model.UserModel;

public interface DataModelToViewObjectConverter {
    public UserVO convertToUserVOFromUserModel(UserModel userModel);
    public ItemVO convertToItemVOFromItemModel(ItemModel itemModel);
}
