package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.dao.jumi.OrderPostDao;
import com.ddzhuan.manage.model.jumi.order.OrderPost;
import com.ddzhuan.manage.service.jumi.OrderPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2020/1/17 14:32
 */
@Service
public class OrderPostServiceImpl implements OrderPostService {

    @Autowired
    private OrderPostDao orderPostDao;


    @Override
    public List<OrderPost> getOrderList() {
        return orderPostDao.queryAllJmPostConfigList();
    }

    @Override
    public void updateOrderPost(OrderPost orderPost) {
        orderPostDao.updateJmPostConfigById(orderPost);
    }

    @Override
    public Integer countOrdConByDeaf() {
        return orderPostDao.countOrdConByDeaf();
    }

    @Override
    public OrderPost getOrderPostById(Long id) {
        return orderPostDao.getOrderPostById(id);
    }
}
