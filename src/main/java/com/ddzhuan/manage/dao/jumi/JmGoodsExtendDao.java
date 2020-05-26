package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.product.JmGoodsExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/27 11:02
 */
public interface JmGoodsExtendDao {

    /**
     * 分页查询自定义字段
     * @param jmGoodsExtend 查询条件
     * @param start 起始页
     * @param end 结束
     * @return 自定义字段列表
     */
    List<JmGoodsExtend> pageJmGoodsExtend(@Param("jmGoodsExtend") JmGoodsExtend jmGoodsExtend,
                                                   @Param("start") Integer start,
                                                   @Param("end") Integer end);


    int countJmGoodsExtend(@Param("jmGoodsExtend") JmGoodsExtend jmGoodsExtend);


    /**
     * 新增自定义字段
     * @param jmGoodsExtend 自定义字段
     */
    int insertJmGoodsExtend(JmGoodsExtend jmGoodsExtend);

    /**
     * 根据主键ID更新巨米自定义字段
     * @param jmGoodsExtend 自定义字段
     */
    int updateJmGoodsExtendById(JmGoodsExtend jmGoodsExtend);

    /**
     * 根据主键ID删除巨米自定义字段
     * @param jmGoodsExtendId 自定义字段Id
     */
    int delJmGoodsExtendById(Long jmGoodsExtendId);

    /**
     * 根据ID查找分类
     * @param jmGoodsExtendId id
     */
    JmGoodsExtend queryJmGoodsExtendById(Long jmGoodsExtendId);

}
