package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.YpProductCategory;
import com.ddzhuan.manage.model.YpProduct;

import java.util.List;

/**
 * 友朋商品接口
 *
 * @author likeke
 * @date 2019/07/15
 */
public interface YpProductService {


    /**
     * 获取商品类别树
     * @param ids 商品类别ids，传空则获取全部的分类
     * @return 商品类别树
     */
    List<YpProductCategory> getYpCategoryList(List<String> ids);

    /**
     * 获取商品列表
     * @param product 商品属性
     * @param keyword   商品查询条件
     * @param pagination 分页参数
     * @return 商品列表
     */
    List<YpProduct> getYpProductListByConditions(YpProduct product, String keyword, Pagination pagination);

    /**
     * 新增商品
     * @param product 商品属性
     */
    void saveYpProduct(YpProduct product);

    /**
     * 批量新增商品种类
     * @param categories 商品种类列表
     */
    void batchSaveYpCategory(List<YpProductCategory> categories);

    /**
     * 根据条形码获取商品信息
     * @param barCode 条形码
     * @return 商品信息
     */
    YpProduct getYpProductByBarCode(String barCode);

    /**
     * 根据条形码删除商品
     * @param barCode 条形码
     */
    void removeProductByBarCode(String barCode);
}
