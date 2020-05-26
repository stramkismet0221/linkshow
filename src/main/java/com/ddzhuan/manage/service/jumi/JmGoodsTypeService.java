package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/26 13:39
 */
public interface JmGoodsTypeService {

    /**
     * 分页查询商品分类列表
     * @param jmGoodsType 查询条件
     * @param pagination  分页对象
     * @return 商品分类列表
     */
    List<JmGoodsType> pageJmGoodsType(JmGoodsType jmGoodsType, Pagination pagination);

    /**
     * 新增商品分类
     * @param jmGoodsType 商品分类对象
     * @return 是否新增成功
     */
    BaseResultInfo insertJmGoodsType(JmGoodsType jmGoodsType);

    /**
     * 根据ID更新商品分类
     * @param jmGoodsType 商品分类对象
     */
    void updateJmGoodsTypeById(JmGoodsType jmGoodsType);

    /**
     * 批量删除商品分类
     * @param jmGoodsTypeId 商品分类ID集合
     * @return 是否删除成功
     */
    boolean delJmGoodsTypeByIds(Long jmGoodsTypeId);

    /**
     * 根据父ID查找分类列表
     * @param pid 父级ID
     */
    List<JmGoodsType> getJmGoodsTypeListByPid(Long pid);

    /**
     * 根据ID查找分类
     * @param jmgoodsTypeId id
     */
    JmGoodsType getJmGoodsTypeById(Long jmgoodsTypeId);

    /**
     * 查询code是否已存在
     * @param code code
     * @return 数量
     */
    int countJmGoodsTypeByCode(String code);

    /**
     * 查询该分类下是否含有子分类
     * @param jmGoodsTypeId 分类ID
     * @return 子分类数量
     */
    int countJmGoodsTypeById(Long jmGoodsTypeId);
}
