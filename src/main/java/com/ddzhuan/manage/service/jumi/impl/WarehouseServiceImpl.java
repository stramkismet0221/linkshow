package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.jumi.WarehouseDao;
import com.ddzhuan.manage.model.jumi.drp.Warehouse;
import com.ddzhuan.manage.service.jumi.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao warehouseDao;
    @Override
    public List<Warehouse> getWarehouseList(String name, Pagination pagination) {
    	List<Warehouse> warehouses = warehouseDao.getWarehouseList(name,pagination.getStart(),pagination.getEnd());
        Integer count = warehouseDao.getWarehouseCount(name);
        pagination.setTotal(count);
        return warehouses;
    }

    @Override
    public boolean addWarehouse(Warehouse warehouse) {
        Integer res = warehouseDao.addWarehouse(warehouse);
        if(res>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkWarehouseName(String name, Long id) {
        List<Warehouse> warehouse = warehouseDao.checkWarehouseName(name,id);
        if(warehouse.size() >0){
            return true;
        }
        return false;
    }

    @Override
    public Warehouse getWarehouseById(Long id) {
        Warehouse warehouse = warehouseDao.getWarehouseById(id);
        return warehouse;
    }

    @Override
    public boolean removeWarehouse(Long id) {
        Integer res = warehouseDao.deleteWarehouse(id);
        if(res>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean editWarehouse(Warehouse warehouse) {
        Integer res = warehouseDao.updateWarehouse(warehouse);
        if(res>0){
            return true;
        }
        return false;
    }
}
