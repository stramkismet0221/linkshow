package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.drp.JmGoodsUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2020/1/9 10:30
 */
public interface JmGoodsUnitDao {


    /**
     * 分页查询商品单位
     * @param jmGoodsUnit 查询条件
     * @param start 起始页
     * @param end 结束
     * @return 商品单位列表
     */
    List<JmGoodsUnit> pageJmGoodsUnit(@Param("jmGoodsUnit") JmGoodsUnit jmGoodsUnit,
                                      @Param("start") Integer start,
                                      @Param("end") Integer end);


    int countJmGoodsUnit(@Param("jmGoodsUnit") JmGoodsUnit jmGoodsUnit);


    /**
     * 新增商品单位
     * @param jmGoodsUnit 商品单位
     */
    int insertJmGoodsUnit(JmGoodsUnit jmGoodsUnit);

    /**
     * 根据主键ID更新巨米商品单位
     * @param jmGoodsUnit 商品单位
     */
    int updateJmGoodsUnitById(JmGoodsUnit jmGoodsUnit);

    /**
     * 根据主键ID删除巨米商品单位
     * @param jmGoodsUnitId 商品单位Id
     */
    int delJmGoodsUnitById(Long jmGoodsUnitId);

    /**
     * 根据ID查找商品单位
     * @param jmGoodsUnitId id
     */
    JmGoodsUnit queryJmGoodsUnitById(Long jmGoodsUnitId);


    List<JmGoodsUnit> queryAllGoodsUnitList();


    List<JmGoodsUnit> getUnitByName(@Param("name") String name,@Param("id") Long id);



}
