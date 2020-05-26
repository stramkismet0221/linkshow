package com.ddzhuan.manage.service.datav.impl;

import com.ddzhuan.manage.dao.datav.SalesDao;
import com.ddzhuan.manage.model.datav.sales.*;
import com.ddzhuan.manage.service.datav.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 销售额
 *
 * @author likeke
 * @date 2019/10/30
 */
@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesDao salesDao;

    @Override
    public SalesTotal getSalesTotal(String oId) {
        return salesDao.querySalesTotal(oId);
    }

    @Override
    public void modifySalesTotal(Long id, Double total, Integer compared) {
        if (id == null) {
            return;
        }
        salesDao.updateSalesTotal(id, total, compared);
    }

    @Override
    public List<SalesStore> getSalesStoreList(String oId) {
        return salesDao.querySalesStoreList(oId);
    }

    @Override
    public SalesQuarter getSalesQuarter(String oId, String year, String quarter) {
        return salesDao.querySalesQuarter(oId, year, quarter);
    }

    @Override
    public List<SalesArea> getSalesAreaList(String oId, Integer type, Integer size) {
        return salesDao.querySalesAreaList(oId, type, size);
    }

    @Override
    public List<SalesProduct> getSalesProductList(String oId, Integer size) {
        return salesDao.querySalesProductList(oId, size);
    }

    @Override
    public List<SalesCategory> getSalesCategoryList(String oId, Integer size, String categoryName) {
        return salesDao.querySalesCategoryList(oId, size, categoryName);
    }

    @Override
    public List<SalesUserAge> getSalesUserAgeList(String oId, Integer type) {
        return salesDao.querySalesUserAgeList(oId, type);
    }

    @Override
    public List<SalesYear> getSalesYearList(String oId) {
        return salesDao.querySalesYearList(oId);
    }

    @Override
    public List<SalesTarget> getSalesTargetList(String oId, Integer type) {
        return salesDao.querySalesTargetList(oId, type);
    }

}
