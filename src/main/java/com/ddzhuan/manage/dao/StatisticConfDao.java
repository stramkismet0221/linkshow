package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.StatisticConf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/8/22 11:36
 */
public interface StatisticConfDao {

    int insertStatisticConf(StatisticConf conf);

    int modifyStatisticConf(StatisticConf conf);

    StatisticConf queryStatisticConfById(Long id);

    List<StatisticConf> queryStatisticCConfListByConditions(@Param("statisticConf") StatisticConf statisticConf, @Param("start") Integer start, @Param("size") Integer size);

    int countStatisticCConfListByConditions(StatisticConf statisticConf);


}
