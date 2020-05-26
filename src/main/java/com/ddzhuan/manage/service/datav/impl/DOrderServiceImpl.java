package com.ddzhuan.manage.service.datav.impl;

import com.ddzhuan.manage.dao.datav.DOrderDao;
import com.ddzhuan.manage.model.datav.DOrder;
import com.ddzhuan.manage.service.datav.DOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * datav -- order
 *
 * @author likeke
 * @date 2019/10/28
 */
@Service
public class DOrderServiceImpl implements DOrderService {

    @Autowired
    private DOrderDao DOrderDao;

    @Override
    public void saveOrder(DOrder order) {
        DOrderDao.insertOrder(order);
    }
}
