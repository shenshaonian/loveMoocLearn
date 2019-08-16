package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.dao.ItemStockDOMapper;
import com.miaoshaproject.dataObject.ItemDO;
import com.miaoshaproject.dataObject.ItemStockDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.emum.EmBussinessError;
import com.miaoshaproject.service.IPromoService;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.userModel.ItemModel;
import com.miaoshaproject.service.userModel.PromoModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 商品信息操作，方法说明看接口
 * @author: 范子祺
 **/
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ValidatorImpl validator;

    /**商品表*/
    @Autowired
    ItemDOMapper itemDOMapper;

    /**库存表*/
    @Autowired
    ItemStockDOMapper itemStockDOMapper;

    /**商品活动信息*/
    @Autowired
    IPromoService promoService;

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException{

        //校验入参
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }


        //转化itemModel-》daoObject
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);

        //写入数据库
        itemDOMapper.insertSelective(itemDO);

        //这个可以把id带回来
        itemModel.setId(itemDO.getId());
        ItemStockDO itemStockDO = this.converItemStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        //返回创建完成的对象

        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = this.convertModelFromDataObjec(itemDO,itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());

        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        //拿到商品表信息
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (null == itemDO){
            return null;
        }
        //拿到库存表中信心
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        //将两个daoObj   转成  model
        ItemModel itemModel = this.convertModelFromDataObjec(itemDO, itemStockDO);

        //获取商品活动信息
//        秒杀活动状态 1还未开始  2进行中 3已结束----拿的商品编号去查的
        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if (promoModel != null && promoModel.getStatus()!=3){
            itemModel.setPromoModel(promoModel);
        }

        return  itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {

        //别人讲的很有道理，初学者可能会先从表中拿出库存数量然后在比较，可以的话，再去表更新库存数据
        int affectedRow = itemStockDOMapper.decreaseStock(itemId,amount);
        if (affectedRow > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemDOMapper.increaseSales(itemId,amount);
    }


    /**
     * 将从数据库中获得商品信息----转成 model
     * @param itemDO
     * @param itemStockDO
     * @return
     */
    private ItemModel convertModelFromDataObjec(ItemDO itemDO,ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }

    /**
     * 前端商品信息 转成  数据库对应信息
     * @param itemModel
     * @return
     */
    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if (itemModel == null){
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);

        return itemDO;
    }

    /**
     * 将商品信息，和商品表id  转成 库存表do
     * @param itemModel
     * @return
     */
    private ItemStockDO converItemStockDOFromItemModel(ItemModel itemModel){
        if (itemModel == null){
            return null;
        }

        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;

    }
}
