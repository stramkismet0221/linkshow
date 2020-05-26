package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.jumi.product.GoodsSupplier;

import java.util.List;

public interface GoodsSupplierService {

    List<GoodsSupplier> getGoodsSupplierList(GoodsSupplier goodsSupplier, Pagination pagination);

    boolean checkGoodsSupplier(String name,Long id);

    Integer addGoodsSupplier(GoodsSupplier goodsSupplier);

    GoodsSupplier getGoodsSupplierById(Long id);

    boolean deleteGoodsSupplier(Long id);

    boolean editGoodsSupplierById(GoodsSupplier goodsSupplier);
}
