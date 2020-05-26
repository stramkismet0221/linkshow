package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.product.JmGoodsManuFatory;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/27 11:02
 */
public interface JmGoodsManuFatoryDao {

    /**
     * 分页查询生产厂家
     * @param jmGoodsManuFatory 查询条件
     * @param start 起始页
     * @param end 结束
     * @return 生产厂家列表
     */
    List<JmGoodsManuFatory> pageJmGoodsManuFactory(@Param("jmGoodsManuFatory") JmGoodsManuFatory jmGoodsManuFatory,
                                                   @Param("start") Integer start,
                                                   @Param("end") Integer end);


    int countJmGoodsManuFactory(@Param("jmGoodsManuFatory") JmGoodsManuFatory jmGoodsManuFatory);


    /**
     * 新增生产厂家
     * @param jmGoodsManuFatory 生产厂家
     */
    int insertJmGoodsManuFatory(JmGoodsManuFatory jmGoodsManuFatory);

    /**
     * 根据主键ID更新巨米生产厂家
     * @param jmGoodsManuFatory 生产厂家
     */
    int updateJmGoodsManuFatoryById(JmGoodsManuFatory jmGoodsManuFatory);

    /**
     * 根据主键ID删除巨米生产厂家
     * @param jmGoodsManuFatoryId 生产厂家Id
     */
    int delJmGoodsManuFatoryById(Long jmGoodsManuFatoryId);

    /**
     * 根据ID查找分类
     * @param jmGoodsManuFatoryId id
     */
    JmGoodsManuFatory queryJmGoodsManuFatoryById(Long jmGoodsManuFatoryId);

    List<JmGoodsManuFatory> queryAllJmGoodsManuFatory();
}
