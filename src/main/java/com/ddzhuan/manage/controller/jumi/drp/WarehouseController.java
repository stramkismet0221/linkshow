package com.ddzhuan.manage.controller.jumi.drp;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.City;
import com.ddzhuan.manage.model.District;
import com.ddzhuan.manage.model.Province;
import com.ddzhuan.manage.model.jumi.drp.Warehouse;
import com.ddzhuan.manage.service.RegionService;
import com.ddzhuan.manage.service.jumi.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/jumi/drp/warehouse/")
public class WarehouseController extends BaseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private RegionService regionService;

    @RequestMapping("list")
    public String getWarehouseList(String name, Model model, Pagination pagination){
        List<Warehouse> warehouses = warehouseService.getWarehouseList(name,pagination);
        model.addAttribute("warehouses",warehouses);
        model.addAttribute("names",name);
        return "jumi/drp/warehouse/warehouselist";
    }

    @RequestMapping("toadd")
    public String toAdd(Model model){
        List<Province> provinces = regionService.getProvinceList();
        List<City> citys = regionService.getCityList(1L);
        List<District> districts = regionService.getDistrictList(1L);
        model.addAttribute("provinces",provinces);
        model.addAttribute("citys",citys);
        model.addAttribute("districts",districts);

        return "jumi/drp/warehouse/addwarehouse";
    }

    /**
     * 添加
     * @param warehouse
     * @return
     */
    @RequestMapping("addwarehouse")
    @ResponseBody
    public BaseResultInfo addWarehouse(Warehouse warehouse){
        BaseResultInfo resultInfo = new BaseResultInfo();
        boolean res = warehouseService.addWarehouse(warehouse);
        resultInfo.setSuccess(res);
        return resultInfo;
    }

    /**
     * 校验姓名
     * @param name
     * @param id
     * @return
     */
    @RequestMapping("checkname")
    @ResponseBody
    public BaseResultInfo checkName(String name,Long id){
        BaseResultInfo resultInfo = new BaseResultInfo();
        boolean res = warehouseService.checkWarehouseName(name,id);
        resultInfo.setSuccess(res);
        return resultInfo;
    }

    @RequestMapping("toedit")
    public String toEditWarehouse(Long id,Model model,Integer type){
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        List<Province> provinces = regionService.getProvinceList();
        List<City> citys = regionService.getCityList(warehouse.getProvinceId());
        List<District> districts = regionService.getDistrictList(warehouse.getCityId());
        model.addAttribute("provinces",provinces);
        model.addAttribute("citys",citys);
        model.addAttribute("districts",districts);
        model.addAttribute("warehouse",warehouse);
        if(type==1){
            return "jumi/drp/warehouse/warehouseinfo";
        }
        return "jumi/drp/warehouse/modifywarehouse";
    }

    @RequestMapping("removewarehouse")
    @ResponseBody
    public BaseResultInfo removeWarehouse(Long id){
        BaseResultInfo resultInfo = new BaseResultInfo();
        boolean res = warehouseService.removeWarehouse(id);
        resultInfo.setSuccess(res);
        return resultInfo;
    }

    /**
            * 修改仓库
     * @param warehouse
     * @return
     */
    @RequestMapping("editwarehouse")
    @ResponseBody
    public BaseResultInfo editWarehouse(Warehouse warehouse){
        BaseResultInfo resultInfo = new BaseResultInfo();
        boolean res = warehouseService.editWarehouse(warehouse);
        resultInfo.setSuccess(res);
        return resultInfo;
    }




}
