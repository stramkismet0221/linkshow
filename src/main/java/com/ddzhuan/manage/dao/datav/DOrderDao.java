package com.ddzhuan.manage.dao.datav;


import com.ddzhuan.manage.model.datav.DOrder;

/**
 * datav -- order
 *
 * @author likeke
 * @date 2019/10/28
 */
public interface DOrderDao {

    /**
     * datav order 新增
     * @param order 订单信息
     */
    void insertOrder(DOrder order);
}
