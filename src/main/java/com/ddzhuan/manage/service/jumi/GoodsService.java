package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.Goods;
import com.ddzhuan.manage.model.jumi.product.GoodsSelectFields;

import java.util.List;

/**
 * 商品信息接口
 *
 * @author likeke
 * @date 2020/01/09
 */
public interface GoodsService {

    /**
     * 获取商品信息列表
     *
     * @param pagination 分页参数
     * @param fields     查询属性
     * @param type       类型：1、实物商品 2、虚拟商品 3、卡包商品 4、原材料
     * @return 商品信息列表
     */
    List<Goods> getGoodsInfoList(GoodsSelectFields fields, Integer type, Pagination pagination);

    /**
     * 根据id获取商品信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    Goods getGoodsInfoById(Long id);

    /**
     * 保存商品信息
     *
     * @param goods 商品信息
     */
    BaseResultInfo saveGoodsInfo(Goods goods);

    /**
     * 修改商品信息
     *
     * @param goods 商品信息
     */
    BaseResultInfo modifyGoodsInfo(Goods goods);

    /**
     * 删除商品信息
     *
     * @param id 商品id
     */
    void removeGoodsInfoById(Long id);

}
