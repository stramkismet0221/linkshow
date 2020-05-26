package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.jumi.JmGoodsExtendDao;
import com.ddzhuan.manage.model.jumi.product.JmGoodsExtend;
import com.ddzhuan.manage.service.jumi.JmGoodsExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/30 10:57
 */
@Service
public class JmGoodsExtendServiceImpl implements JmGoodsExtendService {

    @Autowired
    private JmGoodsExtendDao jmGoodsExtendDao;



    @Override
    public List<JmGoodsExtend> pageJmGoodsExtend(JmGoodsExtend jmGoodsExtend, Pagination pagination) {
        List<JmGoodsExtend> jmGoodsExtends = jmGoodsExtendDao.pageJmGoodsExtend(jmGoodsExtend,pagination.getStart(),pagination.getEnd());
        int count = jmGoodsExtendDao.countJmGoodsExtend(jmGoodsExtend);
        pagination.setTotal(count);
        return jmGoodsExtends;
    }

    @Override
    public void insertJmGoodsExtend(JmGoodsExtend jmGoodsExtend) {
        jmGoodsExtendDao.insertJmGoodsExtend(jmGoodsExtend);
    }

    @Override
    public void updateJmGoodsExtendById(JmGoodsExtend jmGoodsExtend) {
        jmGoodsExtendDao.updateJmGoodsExtendById(jmGoodsExtend);
    }

    @Override
    public void delJmGoodsExtendByIds(Long jmGoodsExtendId) {
        jmGoodsExtendDao.delJmGoodsExtendById(jmGoodsExtendId);
    }

    @Override
    public JmGoodsExtend getJmGoodsExtendById(Long jmGoodsExtendId) {


        JmGoodsExtend jmGoodsExtend = jmGoodsExtendDao.queryJmGoodsExtendById(jmGoodsExtendId);
        if (!StringUtils.isEmpty(jmGoodsExtend.getFieldValue())) {
            if (!StringUtils.isEmpty(jmGoodsExtend.getFieldValue())) {
                jmGoodsExtend.setItems(Arrays.asList(jmGoodsExtend.getFieldValue().split(",")));
            }
        }
        return jmGoodsExtend;
    }
}
