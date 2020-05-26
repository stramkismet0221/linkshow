package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.product.GoodsSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSupplierDao {

    //获取供货商
    List<GoodsSupplier> getGoodsSupplierList(@Param("supplier") GoodsSupplier supplier, @Param("start") Integer start, @Param("size") Integer size);

    Integer countGoodsSupplier(GoodsSupplier goodsSupplier);

    Integer addGoodsSupplier(GoodsSupplier goodsSupplier);

    List<GoodsSupplier> checkGoodsSupplierName(@Param("name") String name,@Param("id") Long id);

    GoodsSupplier getGoodsSupplierById(Long id);

    Integer deleteGoodsSupplierById(Long id);

    Integer updateGoodsSupplier(GoodsSupplier goodsSupplier);

}
