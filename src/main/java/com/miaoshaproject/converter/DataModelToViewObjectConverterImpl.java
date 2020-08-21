package com.miaoshaproject.converter;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.model.ItemModel;
import com.miaoshaproject.model.UserModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DataModelToViewObjectConverterImpl implements DataModelToViewObjectConverter {
    @Override
    public UserVO convertToUserVOFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);

        return userVO;
    }

    @Override
    public ItemVO convertToItemVOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }

        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            itemVO.setPromoStatus(0);
        }

        return itemVO;
    }
}
