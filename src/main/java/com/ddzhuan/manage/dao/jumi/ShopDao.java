package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.jumi.shop.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    List<Shop> getShopList(@Param("shop") Shop shop, @Param("start") Integer start, @Param("size") Integer size);

    Integer getShopCount(Shop shop);
    //Integer addShop(Shop shop);


}
