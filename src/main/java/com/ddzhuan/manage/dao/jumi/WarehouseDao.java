package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.drp.Warehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseDao {

    /**
     * 列表
     * @param name
     * @param start
     * @param size
     * @return
     */
    List<Warehouse> getWarehouseList(@Param("name")String name, @Param("start") Integer start, @Param("size") Integer size);

    Integer getWarehouseCount(@Param("name")String name);

    /**
     * 添加
     * @param warehouse
     * @return
     */
    Integer addWarehouse(Warehouse warehouse);

    List<Warehouse> checkWarehouseName(@Param("name") String name,@Param("id") Long id);

    Warehouse getWarehouseById(Long id);

    Integer updateWarehouse(Warehouse warehouse);

    Integer deleteWarehouse(Long id);

}
