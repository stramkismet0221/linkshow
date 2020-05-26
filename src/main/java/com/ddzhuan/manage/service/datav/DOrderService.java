package com.ddzhuan.manage.service.datav;

import com.ddzhuan.manage.model.datav.DOrder;

/**
 * datav -- order
 *
 * @author likeke
 * @date 2019/10/28
 */
public interface DOrderService {

    /**
     * 新增datav订单
     * @param order 订单信息
     */
    void saveOrder(DOrder order);
}
