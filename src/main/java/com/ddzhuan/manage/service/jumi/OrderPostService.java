package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.model.jumi.order.OrderPost;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2020/1/17 14:32
 */
public interface OrderPostService {

    List<OrderPost> getOrderList();

    void updateOrderPost(OrderPost orderPost);

    Integer countOrdConByDeaf();

    OrderPost getOrderPostById(Long id);
}
