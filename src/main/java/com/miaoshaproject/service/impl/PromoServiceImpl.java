package com.miaoshaproject.service.impl;

import com.miaoshaproject.converter.DataObjectToDataModelConverter;
import com.miaoshaproject.dao.PromoDOMapper;
import com.miaoshaproject.dao.dataobject.PromoDO;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Autowired
    private DataObjectToDataModelConverter dataObjectToDataModelConverter;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        PromoModel promoModel = dataObjectToDataModelConverter.convertToPromoModelFromPromoDO(promoDO);
        if (promoModel == null) {
            return null;
        }

        DateTime now = new DateTime();
        if (promoModel.getStartDate().isAfter(now)) {
            promoModel.setStatus(1);
        } else if (promoModel.getEndDate().isBefore(now)) {
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }
}
