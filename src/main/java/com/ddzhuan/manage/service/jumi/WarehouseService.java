package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.jumi.drp.Warehouse;

import java.util.List;

public interface WarehouseService {


    List<Warehouse> getWarehouseList(String name, Pagination pagination);

    boolean addWarehouse(Warehouse warehouse);

    boolean checkWarehouseName(String name,Long id);

    Warehouse getWarehouseById(Long id);

    boolean removeWarehouse(Long id);

    boolean editWarehouse(Warehouse warehouse);
}
