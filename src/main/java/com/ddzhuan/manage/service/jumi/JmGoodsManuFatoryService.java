package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.JmGoodsManuFatory;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/27 11:12
 */
public interface JmGoodsManuFatoryService {

    /**
     * 分页查询生产厂家列表
     * @param jmGoodsManuFatory 查询条件
     * @param pagination  分页对象
     * @return 生产厂家列表
     */
    List<JmGoodsManuFatory> pageJmGoodsManuFatory(JmGoodsManuFatory jmGoodsManuFatory, Pagination pagination);

    /**
     * 新增生产厂家
     * @param jmGoodsManuFatory 生产厂家对象
     * @return 是否新增成功
     */
    void insertJmGoodsManuFatory(JmGoodsManuFatory jmGoodsManuFatory);

    /**
     * 根据ID更新生产厂家
     * @param jmGoodsManuFatory 生产厂家对象
     */
    void updateJmGoodsManuFatoryById(JmGoodsManuFatory jmGoodsManuFatory);

    /**
     * 批量删除生产厂家
     * @param jmGoodsManuFatoryId 生产厂家ID集合
     * @return 是否删除成功
     */
    void delJmGoodsManuFatoryByIds(Long jmGoodsManuFatoryId);

    /**
     * 根据ID查找分类
     * @param jmGoodsManuFatory id
     */
    JmGoodsManuFatory getJmGoodsManuFatoryById(Long jmGoodsManuFatory);

    List<JmGoodsManuFatory> getAllJmGoodsManuFatory();
}
