package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.model.City;
import com.ddzhuan.manage.model.District;
import com.ddzhuan.manage.model.Province;
import com.ddzhuan.manage.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/region/")
public class RegionController extends BaseController {

    @Autowired
    private RegionService regionService;

    /**
     * 获取省
     * @return
     */
    @RequestMapping("getprovince")
    @ResponseBody
    public List<Province> getProvinceLiswt(){
        List<Province> provinces = regionService.getProvinceList();
        return provinces;
    }

    /**
     * 获取市
     * @param pid
     * @return
     */
    @RequestMapping("getcity")
    @ResponseBody
    public List<City> getCityList(Long pid){
        List<City> cities = regionService.getCityList(pid);
        return cities;
    }

    /**
     * 获取县
     * @param cid
     * @return
     */
    @RequestMapping("getdistrict")
    @ResponseBody
    public List<District> getDistrictList(Long cid){
        List<District> districts = regionService.getDistrictList(cid);
        return districts;
    }








}
