package com.miaoshaproject.converter;

import com.miaoshaproject.dao.dataobject.*;
import com.miaoshaproject.model.ItemModel;
import com.miaoshaproject.model.PromoModel;
import com.miaoshaproject.model.UserModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataObjectToDataModelConverterImpl implements DataObjectToDataModelConverter {
    public UserModel convertToUserModelFromUserPasswordDO(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }

        UserModel userModel = new UserModel.UserModelBuilder().build();
        BeanUtils.copyProperties(userDO, userModel);

        // can specifically control the property sets
//        UserModel userModel = new UserModel.UserModelBuilder()
//                                .setAge(userDO.getAge())
//                                .setGender(userDO.getGender())
//                                .setId(userDO.getId())
//                                .setName(userDO.getName())
//                                .setRegisterMode(userDO.getRegisterMode())
//                                .setTelephone(userDO.getTelephone())
//                                .setThirdPartyId(userDO.getThirdPartyId())
//                                .build();

        if (userPasswordDO != null) {
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        }

        return userModel;
    }

    @Override
    public ItemModel convertToItemModelFromItemStockDO(ItemDO itemDO, ItemStockDO itemStockDO) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }

    @Override
    public PromoModel convertToPromoModelFromPromoDO(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));

        return promoModel;
    }
}
