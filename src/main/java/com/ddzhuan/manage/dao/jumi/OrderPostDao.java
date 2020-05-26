package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.order.OrderPost;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2020/1/17 14:21
 */
public interface OrderPostDao {

    /**
     *  查询配送设置
     * @return 配送设置列表
     */
    List<OrderPost> queryAllJmPostConfigList();

    /**
     * 编辑配送设置
     * @param jmPostConfig 配送设置
     * @return 编辑数量
     */
    Integer updateJmPostConfigById(OrderPost jmPostConfig);

    Integer countOrdConByDeaf();

    OrderPost getOrderPostById(Long id);
}
