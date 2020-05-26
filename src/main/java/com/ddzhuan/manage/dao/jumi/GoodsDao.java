package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.product.Goods;
import com.ddzhuan.manage.model.jumi.product.GoodsSelectFields;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息Dao
 *
 * @author likeke
 * @date 2020/01/09
 */
public interface GoodsDao {

    /**
     * 获取商品信息列表
     *
     * @param fields 查询属性
     * @param type   类型：1、实物商品 2、虚拟商品 3、卡包商品 4、原材料
     * @param start  分页开始
     * @param end    分页结束
     * @return 商品信息列表
     */
    List<Goods> queryGoodsInfoList(@Param("fields") GoodsSelectFields fields, @Param("type") Integer type,
                                   @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 获取商品信息记录数
     *
     * @param fields 查询属性
     * @param type   类型：1、实物商品 2、虚拟商品 3、卡包商品 4、原材料
     * @return 商品信息记录数
     */
    Integer countGoodsInfoList(@Param("fields") GoodsSelectFields fields, @Param("type") Integer type);

    /**
     * 获取商品信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    Goods queryGoodsInfoById(@Param("id") Long id);

    /**
     * 保存商品信息
     *
     * @param goods 商品信息
     */
    Integer insertGoodsInfo(Goods goods);

    /**
     * 修改商品信息
     *
     * @param goods 商品信息
     */
    void updateGoodsInfo(Goods goods);

    /**
     * 删除商品信息
     *
     * @param id 商品信息id
     */
    void deleteGoodsInfoById(Long id);

    /**
     * 批量获取商品基础信息
     *
     * @param ids 商品ids
     * @return 商品基本信息
     */
    List<Goods> queryGoodsListByIds(@Param("ids") List<Long> ids);

}
