package com.miaoshaproject.controller;

import com.miaoshaproject.controller.response.CommonReturnType;
import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.converter.DataModelToViewObjectConverter;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller("item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private DataModelToViewObjectConverter dataModelToViewObjectConverter;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType create(@RequestParam(name = "title") String title,
                                   @RequestParam(name = "description") String description,
                                   @RequestParam(name = "price") BigDecimal price,
                                   @RequestParam(name = "stock") Integer stock,
                                   @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel retItemModel = itemService.create(itemModel);
        ItemVO itemVO = dataModelToViewObjectConverter.convertToItemVOFromItemModel(retItemModel);

        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);

        ItemVO itemVO = dataModelToViewObjectConverter.convertToItemVOFromItemModel(itemModel);

        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType list() {
        List<ItemModel> itemModelList = itemService.listItem();

        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = dataModelToViewObjectConverter.convertToItemVOFromItemModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }
}
