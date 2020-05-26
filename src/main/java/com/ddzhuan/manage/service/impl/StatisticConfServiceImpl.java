package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.StatisticConfDao;
import com.ddzhuan.manage.model.StatisticConf;
import com.ddzhuan.manage.service.StatisticConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

/**
 * @author jiang yong tao
 * @date 2019/8/22 13:40
 */
@Service
public class StatisticConfServiceImpl implements StatisticConfService {


    @Autowired
    private StatisticConfDao statisticConfDao;

    @Override
    public Boolean addStatisticConf(StatisticConf statisticConf) {
        return statisticConfDao.insertStatisticConf(statisticConf) == 1;
    }

    @Override
    public Boolean modifyStatisticConf(StatisticConf statisticConf) {
        Assert.isTrue(Objects.nonNull(statisticConf.getId()),"业务ID不能为空");
        return statisticConfDao.modifyStatisticConf(statisticConf) == 1;
    }

    @Override
    public StatisticConf getStatisticConfById(Long id) {
        return statisticConfDao.queryStatisticConfById(id);
    }

    @Override
    public List<StatisticConf> getStatisticConfListByConditions(StatisticConf statisticConf, Pagination pagination) {
        int count = statisticConfDao.countStatisticCConfListByConditions(statisticConf);
        pagination.setTotal(count);
        List<StatisticConf> confs = statisticConfDao.queryStatisticCConfListByConditions(statisticConf,pagination.getStart(),pagination.getEnd());
        return confs;
    }
}
