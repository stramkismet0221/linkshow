package com.ddzhuan.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.YpTerminal;
import com.ddzhuan.manage.model.YpTerminalCargo;
import com.ddzhuan.manage.model.YpTerminalConfig;
import com.ddzhuan.manage.model.YpTerminalPlace;
import com.ddzhuan.manage.service.YpTerminalService;
import com.ddzhuan.manage.tool.yopoint.YoPointApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 友朋设备管理
 *
 * @author likeke
 * @date 2019/6/19
 */
@Controller
@RequestMapping("/terminal/")
public class TerminalManageController extends BaseController {

    @Autowired
    private YpTerminalService ypTerminalService;

    /**
     * 设备列表
     * <pre>
     *  1、请求友朋接口，获取列表数据，更新到数据库中（此步骤后续修改）
     *  2、直接查询数据库，渲染页面
     * </pre>
     * @param pagination 分页
     * @return 页面
     */
    @RequestMapping(value = "getterminallist", method = RequestMethod.GET)
    public String getTerminalPage(HttpServletRequest request, HttpServletResponse response, Model model,
                                  Pagination pagination,YpTerminal terminal) {
//        saveTerminals();
        List<YpTerminal> ypTerminals = ypTerminalService.getYpTerminalListByConditions(terminal, null, pagination);
        model.addAttribute("terminals", ypTerminals);
        model.addAttribute("terminal", terminal);
        return "terminal/terminallist";
    }

    /**
     * 调友朋接口，获取所有的数据保存到数据库
     */
    private void saveTerminals() {
        YoPointApi api = new YoPointApi();
        String jsonStr = api.getTerminalList(null);
        JSONObject result = JSONObject.parseObject(jsonStr);
        JSONObject data = JSONObject.parseObject(result.getString("data"));
        JSONArray terminalJsonArray = JSONObject.parseArray(data.getString("data"));

        List<YpTerminal> ypTerminals = new ArrayList<>(terminalJsonArray.size());
        YpTerminal ypTerminal = null;
        YpTerminalConfig config = null;
        YpTerminalPlace place = null;
        for (int i = 0; i < terminalJsonArray.size(); i++) {
            JSONObject job = terminalJsonArray.getJSONObject(i);
            JSONObject configJob = JSONObject.parseObject(job.getString("TerminalConfigVO"));
            JSONObject placeJob = JSONObject.parseObject(configJob.getString("PlaceVO"));
            JSONArray cargoJArr = JSONObject.parseArray(job.getString("CargoList"));
            // 货道详情
            List<YpTerminalCargo> cargoes = new ArrayList<>(cargoJArr.size());
            YpTerminalCargo cargo = null;
            for (int j = 0; j < cargoJArr.size(); j++) {
                JSONObject cargoJob = cargoJArr.getJSONObject(j);
                cargo = new YpTerminalCargo();
                // 货道id,自定义为uuid,唯一
                cargo.setId(UUID.randomUUID().toString().replaceAll("-",""));
                cargo.setTerminalId(job.get("id").toString());
                cargo.setCapacity(Integer.parseInt(cargoJob.get("Capacity").toString()));
                cargo.setDisplayName(cargoJob.get("DisplayName").toString());
                cargo.setCabinetName(cargoJob.get("DisplayName").toString());
                cargo.setStock(Integer.parseInt(cargoJob.get("Stock").toString()));
                cargo.setSellStatus(cargoJob.get("SellStatus").toString().equals("true") ? 1 : 0);
                cargo.setBarCode(cargoJob.get("BarCode").toString());
                cargo.setColdStatus(cargoJob.get("ColdStatus").toString().equals("true") ? 1 : 0);
                cargo.setHotStatus(cargoJob.get("ColdStatus").toString().equals("true") ? 1 : 0);
                cargoes.add(cargo);
            }
            // 场地
            place = new YpTerminalPlace();
            place.setId(configJob.get("PlaceID").toString());
            place.setName(placeJob.get("Name").toString());
            place.setProvince(placeJob.get("Province").toString());
            place.setCity(placeJob.get("City").toString());
            place.setDistrict(placeJob.get("District").toString());
            place.setSubject(placeJob.get("Subject").toString());
            // 运营配置
            config = new YpTerminalConfig();
            config.setTerminalId(job.get("id").toString());
            config.setAddress(configJob.get("Address").toString());
            config.setPlaceId(configJob.get("PlaceID").toString());
            config.setPriceRuleId(configJob.get("PriceRuleID").toString());
            config.setVirtualShelfId(configJob.get("VirtualShelfID").toString());
            config.setYpTerminalPlace(place);
            // 设备
            ypTerminal = new YpTerminal();
            ypTerminal.setId(job.get("id").toString());
            ypTerminal.setName(job.get("Name").toString());
            ypTerminal.setDeviceCode(job.get("DeviceCode").toString());
            ypTerminal.setCabinetCount(Integer.parseInt(job.get("CabinetCount").toString()));
            ypTerminal.setYpTerminalConfig(config);
            ypTerminal.setYpTerminalCargoList(cargoes);
            ypTerminals.add(ypTerminal);
        }
        try {
            ypTerminalService.batchSaveYpTerminal(ypTerminals);
        } catch (Exception ex) {
            log.error("批量保存设备接口异常");
            ex.printStackTrace();
        }
    }
}
