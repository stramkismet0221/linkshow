package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.drp.JmErpSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JmErpSettingDao {

    List<JmErpSetting> queryErpConfig();

    Integer updateErpConfig(JmErpSetting jmErpSetting);
}
