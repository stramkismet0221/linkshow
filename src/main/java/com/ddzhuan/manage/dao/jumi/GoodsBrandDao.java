package com.ddzhuan.manage.dao.jumi;


import com.ddzhuan.manage.model.jumi.product.GoodsBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsBrandDao {

    /**
     * 查询商品品牌
     * @param name
     * @param start
     * @param size
     * @return
     */
    List<GoodsBrand> queryBrandListByName(@Param("name") String name, @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 查询数量
     * @param name
     * @return
     */
    Integer countBrandListByName(@Param("name")String name);

    /**
     * 添加商品品牌
     * @param goodsBrand
     * @return
     */
    Integer insertGoodsBrand(GoodsBrand goodsBrand);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Integer deleteGoodsBrandById(Long id);

    /**
     * 根据品牌名称查询是否有重复的
     * @param name
     * @return
     */
    List<GoodsBrand> getGoodsBrandByName(@Param("name") String name,@Param("id") Long id);

    Integer maxSno();

    Integer snoAddOne(Integer sno);

    Integer reduceOne(Integer sno);

    Integer editSno(@Param("osno") Integer osno,@Param("nsno") Integer nsno);

    GoodsBrand getGoodsBrandById(Long id);

    //修改品牌
    Integer updateGoodsBrand(GoodsBrand goodsBrand);
}
