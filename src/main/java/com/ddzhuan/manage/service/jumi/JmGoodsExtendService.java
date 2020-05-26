package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.jumi.product.JmGoodsExtend;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/27 11:12
 */
public interface JmGoodsExtendService {

    /**
     * 分页查询自定义字段列表
     * @param jmGoodsExtend 查询条件
     * @param pagination  分页对象
     * @return 自定义字段列表
     */
    List<JmGoodsExtend> pageJmGoodsExtend(JmGoodsExtend jmGoodsExtend, Pagination pagination);

    /**
     * 新增自定义字段
     * @param jmGoodsExtend 自定义字段对象
     */
    void insertJmGoodsExtend(JmGoodsExtend jmGoodsExtend);

    /**
     * 根据ID更新自定义字段
     * @param jmGoodsExtend 自定义字段对象
     */
    void updateJmGoodsExtendById(JmGoodsExtend jmGoodsExtend);

    /**
     * 删除自定义字段
     * @param jmGoodsExtendId 自定义字段ID
     */
    void delJmGoodsExtendByIds(Long jmGoodsExtendId);

    /**
     * 根据ID查找分类
     * @param jmGoodsExtendId id
     */
    JmGoodsExtend getJmGoodsExtendById(Long jmGoodsExtendId);
}
