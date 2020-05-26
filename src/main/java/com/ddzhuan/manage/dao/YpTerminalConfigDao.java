package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.YpTerminalConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友朋设备运营配置
 *
 * @author likeke
 * @date 2019/06/20
 */
public interface YpTerminalConfigDao {

    /**
     * 批量添加设备运营配置
     * @param ypTerminalConfigs 配置列表
     */
    void batchInsertYpTerminalConfig(@Param("ypTerminalConfigs") List<YpTerminalConfig> ypTerminalConfigs);
}
