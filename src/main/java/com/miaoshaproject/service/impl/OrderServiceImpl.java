package com.miaoshaproject.service.impl;

import com.miaoshaproject.converter.DataModelToDataObjectConverter;
import com.miaoshaproject.converter.DataObjectToDataModelConverter;
import com.miaoshaproject.dao.OrderDOMapper;
import com.miaoshaproject.dao.SequenceDOMapper;
import com.miaoshaproject.dao.dataobject.OrderDO;
import com.miaoshaproject.dao.dataobject.SequenceDO;
import com.miaoshaproject.error.BusinessErrorEnum;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.model.ItemModel;
import com.miaoshaproject.model.OrderModel;
import com.miaoshaproject.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private DataModelToDataObjectConverter dataModelToDataObjectConverter;

    @Autowired
    private DataObjectToDataModelConverter dataObjectToDataModelConverter;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException {
        // 1. check basic information
        // 校验下单状态， 下单的商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }

        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }

        if (amount <= 0 || amount > 99) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "购买数量信息有误");
        }

        if (promoId != null) {
            if (promoId.intValue() != itemModel.getPromoModel().getId()) {
                throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "活动信息不正确");
            } else if (itemModel.getPromoModel().getStatus() != 2) {
                throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "活动信息不在进行中");
            }
        }

        // 2. decrease stock number in db
        // 落单减库存，(支付减库存容易超卖)
        boolean result = itemService.decreaseStock(itemId, amount);
        if (!result) {
            throw new BusinessException(BusinessErrorEnum.STOCK_NOT_ENOUGH);
        }

        // 3. order write to db
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setOrderPrice(itemModel.getPrice().multiply(BigDecimal.valueOf(amount)));

        // generate transaction order
        // when transaction failed, the transaction roll back, then the sequence number will also roll back
        // but, we want the sequence number to be continue even if it failed
        orderModel.setId(generateOrderNO());
        OrderDO orderDO = dataModelToDataObjectConverter.convertToOrderDOFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        // increase item sales in item
        itemService.increaseSales(itemId, amount);

        // 4. return to front page
        return orderModel;
    }

    // no matter if this method is in a new transaction, it will require a transaction
    // no matter if it is succeeded or not, the next transaction will be a new transaction
    // always generate a new sequence, sequence # will be be reused if transaction failed
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNO() {
        // order# has 16 bits
        StringBuilder sb = new StringBuilder();

        // first 8 bits are year, month, date
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        sb.append(nowDate);

        // middle 6 bits are auto increase sequence
        // sequence needs a max value, and init value
        // when sequence current value is greater than the max value,
        // then sequence current value set to init value
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);

        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            sb.append(0);
        }
        sb.append(sequenceStr);

        // last two digits are used to sharding tables
        sb.append("00");

        return sb.toString();
    }
}
