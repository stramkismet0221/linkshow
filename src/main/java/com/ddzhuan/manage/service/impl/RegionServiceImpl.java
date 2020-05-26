package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.dao.RegionDao;
import com.ddzhuan.manage.model.City;
import com.ddzhuan.manage.model.District;
import com.ddzhuan.manage.model.Province;
import com.ddzhuan.manage.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public List<Province> getProvinceList() {
        List<Province> provinces= new ArrayList<Province>();
        provinces = regionDao.getProvinceList();
        return provinces;
    }

    @Override
    public List<City> getCityList(Long pid) {
        List<City> cities = new ArrayList<City>();
        cities = regionDao.getCityList(pid);
        return cities;
    }

    @Override
    public List<District> getDistrictList(Long cid) {
        List<District> districts = new ArrayList<District>();
        districts = regionDao.getDistrictList(cid);
        return districts;
    }
}
