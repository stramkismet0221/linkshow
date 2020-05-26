package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.GoodsGroup;

import java.util.List;
import java.util.Map;

public interface JmGoodsGroupService {

    /**
     * 获取商品分类列表
     * @param name
     * @param pagination
     * @return
     */
    List<GoodsGroup> queryGoodsGroupBy(String name, Pagination pagination);

    /**
     * 编辑图片
     * @param fileList
     * @return
     */
    BaseResultInfo updateIconImg(List<Map<String,Object>> fileList);

    Boolean getGoodsGroupByName(String name, Long id);

    /**
     * 添加分组
     * @param goodsGroup
     * @return
     */
    Integer addGoodsGroup(GoodsGroup goodsGroup);

    Integer deleGroupsById(Long id);

    GoodsGroup getGroupsById(Long id);

    Integer editGoodsBrand(GoodsGroup goodsGroup);
}
