package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.order.PurchaseOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2020/2/3 14:41
 */
public interface PurchaseOrderDao {

    List<PurchaseOrder> pagePurchaseList(@Param("purchaseOrder") PurchaseOrder purchaseOrder, @Param("start") Integer start, @Param("end") Integer end);

    Integer countPurchase(@Param("purchaseOrder")PurchaseOrder purchaseOrder);

    Integer insertPurchaseOrder(PurchaseOrder purchaseOrder);

    Integer updatePurchaseOrderById(PurchaseOrder purchaseOrder);

    Integer delPurchaseORderById(Long id);

    PurchaseOrder queryPurchaseById(Long id);

}
