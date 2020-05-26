package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.jumi.JmGoodsManuFatoryDao;
import com.ddzhuan.manage.model.jumi.product.JmGoodsManuFatory;
import com.ddzhuan.manage.service.jumi.JmGoodsManuFatoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/27 11:14
 */
@Service
public class JmGoodsManuFatoryServiceImpl implements JmGoodsManuFatoryService {

    @Autowired
    private JmGoodsManuFatoryDao jmGoodsManuFatoryDao;


    @Override
    public List<JmGoodsManuFatory> pageJmGoodsManuFatory(JmGoodsManuFatory jmGoodsManuFatory, Pagination pagination) {
        List<JmGoodsManuFatory> jmGoodsManuFatories = jmGoodsManuFatoryDao.pageJmGoodsManuFactory(jmGoodsManuFatory,pagination.getStart(),pagination.getEnd());
        int count = jmGoodsManuFatoryDao.countJmGoodsManuFactory(jmGoodsManuFatory);
        pagination.setTotal(count);
        return jmGoodsManuFatories;
    }

    @Override
    public void insertJmGoodsManuFatory(JmGoodsManuFatory jmGoodsManuFatory) {
        jmGoodsManuFatoryDao.insertJmGoodsManuFatory(jmGoodsManuFatory);
    }

    @Override
    public void updateJmGoodsManuFatoryById(JmGoodsManuFatory jmGoodsManuFatory) {
        jmGoodsManuFatoryDao.updateJmGoodsManuFatoryById(jmGoodsManuFatory);
    }

    @Override
    public void delJmGoodsManuFatoryByIds(Long jmGoodsManuFatoryId) {
        jmGoodsManuFatoryDao.delJmGoodsManuFatoryById(jmGoodsManuFatoryId);
    }

    @Override
    public JmGoodsManuFatory getJmGoodsManuFatoryById(Long jmGoodsManuFatory) {
        return jmGoodsManuFatoryDao.queryJmGoodsManuFatoryById(jmGoodsManuFatory);
    }

    @Override
    public List<JmGoodsManuFatory> getAllJmGoodsManuFatory() {
        return jmGoodsManuFatoryDao.queryAllJmGoodsManuFatory();
    }
}
