package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.StatisticConf;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/8/22 13:40
 */
public interface StatisticConfService {

    /**
     * 新增统计
     * @param statisticConf 统计
     * @return 是否成功
     */
    Boolean addStatisticConf(StatisticConf statisticConf);

    /**
     * 编辑统计
     * @param statisticConf 统计
     * @return 是否成功
     */
    Boolean modifyStatisticConf(StatisticConf statisticConf);

    /**
     * 根据ID查询统计信息
     * @param id 统计信息ID
     * @return 统计信息
     */
    StatisticConf getStatisticConfById(Long id);

    /**
     * 分页查询统计列表
     * @param statisticConf 查询条件
     * @param pagination    分页参数
     * @return 统计列表
     */
    List<StatisticConf> getStatisticConfListByConditions(StatisticConf statisticConf, Pagination pagination);
}
