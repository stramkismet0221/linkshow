package com.ddzhuan.manage.service;

import com.ddzhuan.manage.model.City;
import com.ddzhuan.manage.model.District;
import com.ddzhuan.manage.model.Province;

import java.util.List;

public interface RegionService {


    List<Province> getProvinceList();

    List<City> getCityList(Long pid);

    List<District> getDistrictList(Long cid);

}
