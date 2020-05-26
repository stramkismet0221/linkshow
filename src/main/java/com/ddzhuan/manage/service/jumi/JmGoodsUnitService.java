package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.jumi.drp.JmGoodsUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2020/1/9 10:37
 */
public interface JmGoodsUnitService {

    /**
     * 分页查询自定义字段列表
     * @param jmGoodsUnit 查询条件
     * @param pagination  分页对象
     * @return 自定义字段列表
     */
    List<JmGoodsUnit> pageJmGoodsUnit(JmGoodsUnit jmGoodsUnit, Pagination pagination);

    /**
     * 新增自定义字段
     * @param jmGoodsUnit 自定义字段对象
     */
    void insertJmGoodsUnit(JmGoodsUnit jmGoodsUnit);

    /**
     * 根据ID更新自定义字段
     * @param jmGoodsUnit 自定义字段对象
     */
    void updateJmGoodsUnitById(JmGoodsUnit jmGoodsUnit);

   boolean getUnitByName(String name,Long id);


    /**
     * 删除自定义字段
     * @param jmGoodsUnitId 自定义字段ID
     */
    void delJmGoodsUnitByIds(Long jmGoodsUnitId);

    /**
     * 根据ID查找分类
     * @param jmGoodsUnitId id
     */
    JmGoodsUnit getJmGoodsUnitById(Long jmGoodsUnitId);

    /**
     * 查询所有单位
     */
    List<JmGoodsUnit> getAllGoodsUnitList();

}
