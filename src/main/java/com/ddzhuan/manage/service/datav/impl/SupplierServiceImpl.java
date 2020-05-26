package com.ddzhuan.manage.service.datav.impl;

import com.ddzhuan.manage.dao.datav.SupplierDao;
import com.ddzhuan.manage.model.datav.supplier.SupplierArea;
import com.ddzhuan.manage.model.datav.supplier.SupplierQuarter;
import com.ddzhuan.manage.model.datav.supplier.SupplierRank;
import com.ddzhuan.manage.model.datav.supplier.SupplierTotal;
import com.ddzhuan.manage.service.datav.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 运营商采购相关信息统计实现类
 *
 * @author likeke
 * @date 2019/10/23
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Override
    public SupplierTotal getTotalByOId(String oId) {
        Assert.isTrue(oId != null, "oId不能为null");
        return supplierDao.queryTotalByOId(oId);
    }

    @Override
    public List<SupplierRank> getSupplierRankList(String oId, Integer size) {
        if (size == null || size == 0) {
            return new ArrayList<>();
        }
        return supplierDao.querySupplierRankList(oId, size);
    }

    @Override
    public SupplierQuarter getSupplierQuarter(String oId, String year, String quarter) {
        Assert.isTrue(!StringUtils.isEmpty(year), "年度不能为空");
        Assert.isTrue(!StringUtils.isEmpty(quarter), "季度不能为空");
        return supplierDao.querySupplierQuarter(oId, year, quarter);
    }

    @Override
    public List<SupplierArea> getSupplierAreaList(String oId, Integer size, Integer type) {
        if (size == null || size == 0) {
            return new ArrayList<>();
        }
        return supplierDao.querySupplierAreaList(oId, size, type);
    }

}
