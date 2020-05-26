package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.drp.WarnSet;
import com.ddzhuan.manage.model.jumi.drp.WarnSetRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 仓库预警设置Dao
 *
 * @author likeke
 * @date 2020/01/10
 */
public interface WareWarnSetDao {

    /**
     * 保存库存预警信息
     *
     * @param warnSet 库存预警信息
     * @return 主键id
     */
    void insertWareWarnSet(@Param("warnSet") WarnSet warnSet);

    /**
     * 修改库存预警信息
     *
     * @param warnSet 库存预警信息
     */
    void updateWareWarnSet(@Param("warnSet") WarnSet warnSet);

    /**
     * 通过id获取预警设置信息
     *
     * @param id 预警设置信息id
     * @return 预警设置信息
     */
    WarnSet queryWarnSetById(@Param("id") Long id);

    /**
     * 批量插入分仓信息表
     *
     * @param warnSetRelList 分仓信息集合
     */
    void batchInsertRel(@Param("warnSetRelList") List<WarnSetRel> warnSetRelList);

    /**
     * 批量修改分仓信息表
     *
     * @param warnSetRelList 分仓信息集合
     */
    void batchUpdateRel(@Param("warnSetRelList") List<WarnSetRel> warnSetRelList);

    /**
     * 根据warnSetId查询分仓预警配置列表
     *
     * @return 列表
     */
    List<WarnSetRel> queryWarnSetRelList();

    /**
     * 删除分仓预警设置表记录
     */
    void deleteWarnSetRelList();

    /**
     * 根据预警设置id获取分仓信息列表
     *
     * @param warnSetId 预警设置id
     * @return 分仓信息列表
     */
    List<WarnSetRel> queryRelListByWarnSetId(@Param("warnSetId") Long warnSetId);

}
