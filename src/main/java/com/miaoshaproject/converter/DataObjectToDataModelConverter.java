package com.miaoshaproject.converter;

import com.miaoshaproject.dao.dataobject.*;
import com.miaoshaproject.model.ItemModel;
import com.miaoshaproject.model.PromoModel;
import com.miaoshaproject.model.UserModel;

public interface DataObjectToDataModelConverter {
    public UserModel convertToUserModelFromUserPasswordDO(UserDO userDO, UserPasswordDO userPasswordDO);
    public ItemModel convertToItemModelFromItemStockDO(ItemDO itemDO, ItemStockDO itemStockDO);

    public PromoModel convertToPromoModelFromPromoDO(PromoDO promoDO);
}
