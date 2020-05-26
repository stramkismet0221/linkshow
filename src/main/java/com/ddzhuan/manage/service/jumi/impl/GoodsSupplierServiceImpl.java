package com.ddzhuan.manage.service.jumi.impl;


import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.jumi.GoodsSupplierDao;
import com.ddzhuan.manage.model.jumi.product.GoodsSupplier;
import com.ddzhuan.manage.service.jumi.GoodsSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public  class GoodsSupplierServiceImpl implements GoodsSupplierService {

    @Autowired
    private GoodsSupplierDao goodsSupplierDao;
    @Override
    public List<GoodsSupplier> getGoodsSupplierList(GoodsSupplier goodsSupplier, Pagination pagination) {
        Integer count = goodsSupplierDao.countGoodsSupplier(goodsSupplier);
        pagination.setTotal(count);
        List<GoodsSupplier> goodsSuppliers = goodsSupplierDao.getGoodsSupplierList(goodsSupplier,pagination.getStart(),pagination.getEnd());
        return goodsSuppliers;
    }

    @Override
    public boolean checkGoodsSupplier(String name,Long id) {
        List<GoodsSupplier> goodsSuppliers = new ArrayList<GoodsSupplier>();
        goodsSuppliers = goodsSupplierDao.checkGoodsSupplierName(name,id);
        if(goodsSuppliers.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Integer addGoodsSupplier(GoodsSupplier goodsSupplier) {
        Integer res = 0;
        res = goodsSupplierDao.addGoodsSupplier(goodsSupplier);
        return res;
    }

    @Override
    public GoodsSupplier getGoodsSupplierById(Long id) {
        GoodsSupplier goodsSupplier = goodsSupplierDao.getGoodsSupplierById(id);
        return goodsSupplier;
    }

    @Override
    public boolean deleteGoodsSupplier(Long id) {
        Integer res = goodsSupplierDao.deleteGoodsSupplierById(id);
        if(res>0){
            return  true;
        }
        return false;
    }

    @Override
    public boolean editGoodsSupplierById(GoodsSupplier goodsSupplier) {
        int res = goodsSupplierDao.updateGoodsSupplier(goodsSupplier);
        if(res>0){
            return true;
        }
        return false;
    }
}
