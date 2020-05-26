package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.dao.jumi.TestDao;
import com.ddzhuan.manage.model.jumi.product.Test;
import com.ddzhuan.manage.service.jumi.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> getTestList() {
        return testDao.queryTestList();
    }

}
