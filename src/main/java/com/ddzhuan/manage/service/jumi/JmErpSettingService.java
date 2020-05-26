package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.model.jumi.drp.JmErpSetting;

import java.util.List;

public interface JmErpSettingService {

    /**
     * 获取配置列表
     * @return
     */
    List<JmErpSetting> getErpConfigList();

    /**
     * 更新配置文件
     * @param erpSetting
     * @return
     */
    int updateErpConfig(JmErpSetting erpSetting);
}
