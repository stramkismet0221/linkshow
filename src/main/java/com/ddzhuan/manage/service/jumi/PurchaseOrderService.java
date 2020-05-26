package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.jumi.order.PurchaseOrder;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2020/2/3 15:15
 */
public interface PurchaseOrderService {

    /**
     * 购货订单列表
     * @param purchaseOrder
     * @param pagination
     * @return
     */
    List<PurchaseOrder> pagePurchaseList(PurchaseOrder purchaseOrder, Pagination pagination);

    boolean addPurchaseOrder(PurchaseOrder purchaseOrder);

    boolean updatePurchaseOrderById(PurchaseOrder purchaseOrder, Integer ptype);

    boolean delPurchaseOrderById(Long id);

    PurchaseOrder getPurchaseOrderById(Long id);

}
