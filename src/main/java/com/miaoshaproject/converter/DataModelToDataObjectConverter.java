package com.miaoshaproject.converter;

import com.miaoshaproject.dao.dataobject.*;
import com.miaoshaproject.model.ItemModel;
import com.miaoshaproject.model.OrderModel;
import com.miaoshaproject.model.UserModel;

public interface DataModelToDataObjectConverter {
    public UserDO convertToUserDOFromUserModel(UserModel userModel);
    public UserPasswordDO convertToUserPasswordDOFromUserModel(UserModel userModel);

    public ItemDO convertToItemDOFromItemModel(ItemModel itemModel);
    public ItemStockDO convertToItemStockDOFromItemModel(ItemModel itemModel);

    public OrderDO convertToOrderDOFromOrderModel(OrderModel orderModel);
}
