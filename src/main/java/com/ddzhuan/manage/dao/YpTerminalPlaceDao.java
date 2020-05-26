package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.YpTerminalPlace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友朋设备地址Dao
 *
 * @author likeke
 * @date 2019/06/20
 */
public interface YpTerminalPlaceDao {

    /**
     * 批量添加设备地址
     * @param ypTerminalPlaces 设备地址列表
     */
    void batchInsertYpTerminalPlace(@Param("ypTerminalPlaces") List<YpTerminalPlace> ypTerminalPlaces);

    /**
     * 根据id查询设备所在地址
     * @param id
     * @return
     */
    YpTerminalPlace queryYpTerminalPlaceById(@Param("id") String id);

    /**
     * 根据设备场地名称模糊查询ids
     * @param placeName 设备场地名称
     * @return ids
     */
    List<String> queryYpTerminalPlaceIdsByName(@Param("placeName") String placeName);
}
