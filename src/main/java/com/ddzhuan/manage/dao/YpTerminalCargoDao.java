package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.YpTerminalCargo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友朋货道Dao
 *
 * @author likeke
 * @date 2019/06/20
 */
public interface YpTerminalCargoDao {

    /**
     * 批量添加设备货道
     * @param ypTerminalCargoes 设备货道集合
     */
    void batchInsertYpTerminalCargo(@Param("ypTerminalCargoes") List<YpTerminalCargo> ypTerminalCargoes);
}
