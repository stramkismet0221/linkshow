package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.City;
import com.ddzhuan.manage.model.Dictionary;
import com.ddzhuan.manage.model.District;
import com.ddzhuan.manage.model.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionDao {



    List<Province> getProvinceList();

    List<City> getCityList(@Param("pid")Long pid);

    List<District> getDistrictList(@Param("cid") Long cid);

}
