package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.jumi.JmGoodsUnitDao;
import com.ddzhuan.manage.model.jumi.drp.JmGoodsUnit;
import com.ddzhuan.manage.service.jumi.JmGoodsUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/30 10:57
 */
@Service
public class JmGoodsUnitServiceImpl implements JmGoodsUnitService {

    @Autowired
    private JmGoodsUnitDao jmGoodsUnitDao;

    @Override
    public List<JmGoodsUnit> pageJmGoodsUnit(JmGoodsUnit jmGoodsUnit, Pagination pagination) {
        List<JmGoodsUnit> jmGoodsUnits = jmGoodsUnitDao.pageJmGoodsUnit(jmGoodsUnit,pagination.getStart(),pagination.getEnd());
        int count = jmGoodsUnitDao.countJmGoodsUnit(jmGoodsUnit);
        pagination.setTotal(count);
        return jmGoodsUnits;
    }

    @Override
    public void insertJmGoodsUnit(JmGoodsUnit jmGoodsUnit) {
        jmGoodsUnitDao.insertJmGoodsUnit(jmGoodsUnit);
    }

    @Override
    public void updateJmGoodsUnitById(JmGoodsUnit jmGoodsUnit) {
        jmGoodsUnitDao.updateJmGoodsUnitById(jmGoodsUnit);
    }

    @Override
    public void delJmGoodsUnitByIds(Long jmGoodsUnitId) {
        jmGoodsUnitDao.delJmGoodsUnitById(jmGoodsUnitId);
    }

    @Override
    public JmGoodsUnit getJmGoodsUnitById(Long jmGoodsUnitId) {
        return jmGoodsUnitDao.queryJmGoodsUnitById(jmGoodsUnitId);
    }

    @Override
    public List<JmGoodsUnit> getAllGoodsUnitList() {
        return jmGoodsUnitDao.queryAllGoodsUnitList();
    }

    @Override
    public boolean getUnitByName(String name, Long id) {
        List<JmGoodsUnit> jmGoodsUnits = jmGoodsUnitDao.getUnitByName(name,id);
        if(jmGoodsUnits.size()>0){
            return true;
        }
        return false;
    }
}
