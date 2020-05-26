package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.dao.jumi.JmErpSettingDao;
import com.ddzhuan.manage.model.jumi.drp.JmErpSetting;
import com.ddzhuan.manage.service.jumi.JmErpSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JmErpSettingServiceImpl implements JmErpSettingService {

    @Autowired
    JmErpSettingDao erpSettingDao;

    @Override
    public List<JmErpSetting> getErpConfigList() {

        return erpSettingDao.queryErpConfig();
    }

    @Override
    public int updateErpConfig(JmErpSetting erpSetting) {

        return erpSettingDao.updateErpConfig(erpSetting);
    }
}
