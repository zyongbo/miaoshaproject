package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.model.ItemModel;

import java.util.List;

public interface ItemService {
    public ItemModel create(ItemModel itemModel) throws BusinessException;
    public List<ItemModel> listItem();
    public ItemModel getItemById(Integer id);
    public boolean decreaseStock(Integer itemId, Integer amount);
    public void increaseSales(Integer itemId, Integer amount);
}
