package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.PurchaseEnums;
import com.ddzhuan.manage.dao.datav.SupplierDao;
import com.ddzhuan.manage.dao.jumi.GoodsSupplierDao;
import com.ddzhuan.manage.dao.jumi.PurchaseOrderDao;
import com.ddzhuan.manage.model.jumi.order.PurchaseOrder;
import com.ddzhuan.manage.model.jumi.product.GoodsSupplier;
import com.ddzhuan.manage.service.jumi.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiang yong tao
 * @date 2020/2/3 15:18
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    // 购货单号前缀
    private final static String GH_PREFIX = "GH";
    // 购货订单号前缀
    private final static String GHDD_PREFIX = "GHDD";
    // 退货单号前缀
    private final static String GHT_PREFIX = "GHT";



    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    @Autowired
    private GoodsSupplierDao goodsSupplierDao;

    @Override
    public List<PurchaseOrder> pagePurchaseList(PurchaseOrder purchaseOrder, Pagination pagination) {
        GoodsSupplier goodsSupplier = new GoodsSupplier();
        goodsSupplier.setName(purchaseOrder.getKeyWord());
        goodsSupplier.setStates(1);
        Pagination pagination1 = new Pagination(1,Integer.MAX_VALUE);
        List<Long> supplierIds = goodsSupplierDao.getGoodsSupplierList(goodsSupplier,pagination1.getStart(),pagination1.getEnd()).stream().map(GoodsSupplier::getId).collect(Collectors.toList());
        purchaseOrder.setSupplierIds(supplierIds);
        List<PurchaseOrder> purchaseOrders = purchaseOrderDao.pagePurchaseList(purchaseOrder,pagination.getStart(),pagination.getEnd()).stream().peek(v->{
            GoodsSupplier supplier = goodsSupplierDao.getGoodsSupplierById(v.getSupplierId());
            v.setSupplierName(supplier.getName());
        }).collect(Collectors.toList());
        Integer count = purchaseOrderDao.countPurchase(purchaseOrder);
        pagination.setTotal(count);
        return purchaseOrders;
    }

    @Override
    public boolean addPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setStatus(PurchaseEnums.DEAFSTATUS.code);
        String no = String.valueOf(System.currentTimeMillis());
        if (PurchaseEnums.GH.code.equals(purchaseOrder.getType())) {
            purchaseOrder.setGhNo(GHDD_PREFIX + no);
        }

        if (PurchaseEnums.GHDD.code.equals(purchaseOrder.getType())) {
            purchaseOrder.setGhdNo(GHDD_PREFIX + no);
        }

        if (PurchaseEnums.GHT.code.equals(purchaseOrder.getType())) {
            purchaseOrder.setGhtNo(GHT_PREFIX + no);
        }

        return purchaseOrderDao.insertPurchaseOrder(purchaseOrder) == 1;
    }

    @Override
    public boolean updatePurchaseOrderById(PurchaseOrder purchaseOrder, Integer ptype) {
        Assert.isTrue(purchaseOrder.getId() != null,"ID为空");
        if (null != ptype) {
            String no = String.valueOf(System.currentTimeMillis());
            if (PurchaseEnums.GH.code.equals(ptype)) {
                purchaseOrder.setGhNo(GHDD_PREFIX + no);
            }

            if (PurchaseEnums.GHDD.code.equals(ptype)) {
                purchaseOrder.setGhdNo(GHDD_PREFIX + no);
            }

            if (PurchaseEnums.GHT.code.equals(ptype)) {
                purchaseOrder.setType(PurchaseEnums.GHT.code);
                purchaseOrder.setGhtNo(GHT_PREFIX + no);
            }
        }
        return purchaseOrderDao.updatePurchaseOrderById(purchaseOrder) == 1;
    }

    @Override
    public boolean delPurchaseOrderById(Long id) {
        return purchaseOrderDao.delPurchaseORderById(id) > 0;
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderDao.queryPurchaseById(id);
    }
}
