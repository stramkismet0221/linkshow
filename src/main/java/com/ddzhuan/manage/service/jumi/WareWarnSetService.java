package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.model.jumi.drp.WarnSet;
import com.ddzhuan.manage.model.jumi.drp.WarnSetRel;

import java.util.List;

/**
 * 仓库预警设置
 *
 * @author likeke
 * @date 2020/01/10
 */
public interface WareWarnSetService {

    /**
     * 保存/修改库存预警信息
     *
     * @param warnSet 库存预警信息
     */
    void saveWareWarnSet(WarnSet warnSet);

    /**
     * 通过id获取库存预警信息
     *
     * @param id 库存预警信息id
     * @return warnSet
     */
    WarnSet getWareWarnSetById(Long id);

    /**
     * 根据预警设置id获取分仓信息列表
     *
     * @param warnSetId 预警设置id
     * @return 分仓信息列表
     */
    List<WarnSetRel> getRelListByWarnSetId(Long warnSetId);
}
