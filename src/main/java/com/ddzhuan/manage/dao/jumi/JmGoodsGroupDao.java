package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.product.GoodsBrand;
import com.ddzhuan.manage.model.jumi.product.GoodsGroup;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface JmGoodsGroupDao {

    /**
     * 查询数量
     * @param name
     * @return
     */
    Integer countGroupListByName(@Param("name")String name);

    /**
     * 获取商品分类列表
     * @param name
     * @param start
     * @param end
     * @return
     */
    List<GoodsGroup> queryGroupListBy(@Param("name")String name,
                                      @Param("start")long start,
                                      @Param("end")long end);

    /**
     * 根据分组名称查询
     * @param name
     * @param id
     * @return
     */
    List<GoodsGroup> queryGroupByName(@Param("name") String name,
                                      @Param("id") Long id);

    /**
     * 插入分组记录
     */
    Integer insertGoodsGroup(GoodsGroup goodsGroup);

    Integer maxSno();

    Integer snoAddOne(@Param("sno") Integer sno);

    Integer deleGroupsById(@Param("id") Long id);

    GoodsGroup queryGroupById(@Param("id") Long id);

    Integer updateGoodsBrand(GoodsGroup goodsGroup);

    Integer editSno(@Param("osno") Integer osno,@Param("nsno") Integer nsno);

    Integer reduceOne(Integer sno);
}
