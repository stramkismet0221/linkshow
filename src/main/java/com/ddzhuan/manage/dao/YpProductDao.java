package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.YpProductCategory;
import com.ddzhuan.manage.model.YpProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友朋商品Dao
 * @author likeke
 * @date 2019/07/15
 */
public interface YpProductDao {

    /**
     * 获取商品种类数
     * @param ids 商品分类ids
     * @return List
     */
    List<YpProductCategory> queryYpCategoryList(@Param("ids") List<String> ids);

    /**
     * 获取商品列表
     * @param product 商品属性
     * @param keyword 查询条件
     * @param page 当前页
     * @param rows 分页长度
     * @return 商品列表
     */
    List<YpProduct> queryYpProductListByConditions(@Param("product") YpProduct product,
                                                    @Param("keyword") String keyword,
                                                    @Param("page") Integer page,
                                                    @Param("rows") Integer rows);

    /**
     * 获取记录数
     * @param product 商品属性
     * @param keyword 查询条件
     * @return 记录数
     */
    Integer queryYpProductCount(@Param("product") YpProduct product,
                                 @Param("keyword") String keyword);

    /**
     * 批量新增商品种类
     * @param ypProductCategories 商品种类列表
     */
    void batchInsertYpCategory(@Param("ypProductCategories") List<YpProductCategory> ypProductCategories);

    /**
     * 保存商品
     * @param product 商品属性
     */
    void insertYpProduct(@Param("product") YpProduct product);

    /**
     * 根据条形码获取商品信息
     * @param barCode 条形码
     * @return 商品信息
     */
    YpProduct queryYpProductByBarCode(@Param("barCode") String barCode);

    /**
     * 根据商品条形码删除商品
     * @param barCode 商品条形码
     */
    void deleteProductByBarCode(@Param("barCode") String barCode);

    /**
     *  根据商品条形码查询商品
     * @param barCodes 条形码
     * @return 商品列表
     */
    List<YpProduct> queryYpProductListByBarCodes(List<String> barCodes);
}
