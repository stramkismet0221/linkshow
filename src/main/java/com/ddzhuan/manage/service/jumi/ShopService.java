package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.jumi.shop.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getShopList(Shop shop, Pagination pagination);

}
