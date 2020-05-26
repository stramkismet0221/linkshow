package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.jumi.ShopDao;
import com.ddzhuan.manage.model.jumi.shop.Shop;
import com.ddzhuan.manage.service.jumi.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;
    @Override
    public List<Shop> getShopList(Shop shop, Pagination pagination) {
        Integer count = shopDao.getShopCount(shop);
        pagination.setTotal(count);
        List<Shop> shops = shopDao.getShopList(shop,pagination.getStart(),pagination.getEnd());
        return shops;
    }

}
