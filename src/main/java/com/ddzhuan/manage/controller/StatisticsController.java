package com.ddzhuan.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Data;
import com.ddzhuan.manage.model.StatisticConf;
import com.ddzhuan.manage.service.StatisticConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 数据统计中心
 *
 * @author likeke
 * @date 2019/08/21
 */

@Controller
@RequestMapping("/statistics/")
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticConfService statisticConfService;

    @RequestMapping(value = "getstatlist",method = RequestMethod.GET)
    public String getList(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<StatisticConf> statistics = statisticConfService.getStatisticConfListByConditions(new StatisticConf(), new Pagination(1, 2147483647));
        model.addAttribute("statistics", statistics);
        return "/statistics/statlist";
    }

    @RequestMapping(value = "getstatconflist",method = RequestMethod.GET)
    public String getStatConfList(StatisticConf statisticConf, Pagination pagination, Model model) {
        List<StatisticConf> statisticConfs = statisticConfService.getStatisticConfListByConditions(statisticConf,pagination);
        model.addAttribute("statisticConfs",statisticConfs);
        model.addAttribute("statisticConf",statisticConf);
        return "/statistics/statconflist";
    }


    @RequestMapping(value = "toaddstatconf",method = RequestMethod.GET)
    public String toAddStatConf() {
        return "/statistics/addstatconf";
    }

    @RequestMapping(value = "getstatconfbyid",method = RequestMethod.GET)
    public String getStatConfById(Long id,Model model,Integer type) {

        StatisticConf statisticConf = statisticConfService.getStatisticConfById(id);
        model.addAttribute("statisticConf",statisticConf);

        String jsp = "";
        if (OperateTypeEnum.DETAIL.code.equals(type)){
            jsp =  "/statistics/modifystatconf";
        }

        if (OperateTypeEnum.EDIT.code.equals(type)){
            jsp =   "/statistics/statconfdetail";
        }

        return jsp;
    }

    @RequestMapping(value = "addstatconf",method = RequestMethod.POST)
    @ResponseBody
    public BaseResultInfo addStatConf(StatisticConf statisticConf) {
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        try {
            statisticConf.setStatus(1);
            Boolean flag = statisticConfService.addStatisticConf(statisticConf);
            baseResultInfo.setSuccess(flag);
            baseResultInfo.setMsg("保存成功");
        }catch (Exception e){
            log.info(e);
            baseResultInfo.setSuccess(false);
            baseResultInfo.setMsg("保存失败");
        }
        return baseResultInfo;
    }


    @RequestMapping(value = "modifystatconf",method = RequestMethod.POST)
    @ResponseBody
    public BaseResultInfo modifyStatConf(StatisticConf statisticConf) {
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        try {
            Boolean flag = statisticConfService.modifyStatisticConf(statisticConf);
            baseResultInfo.setSuccess(flag);
            baseResultInfo.setMsg("保存成功");
        }catch (Exception e){
            log.info(e);
            baseResultInfo.setSuccess(false);
            baseResultInfo.setMsg("保存失败");
        }
        return baseResultInfo;
    }

    @RequestMapping("datav")
    @ResponseBody
    public List<Data> getDataV(HttpServletRequest request, HttpServletResponse response, Model model) {

        String str  = "[\n" +
                "  {\n" +
                "    \"x\": \"上海\",\n" +
                "    \"y\": 29,\n" +
                "    \"s\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"x\": \"广州\",\n" +
                "    \"y\": 27,\n" +
                "    \"s\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"x\": \"北京\",\n" +
                "    \"y\": 23,\n" +
                "    \"s\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"x\": \"深圳\",\n" +
                "    \"y\": 20,\n" +
                "    \"s\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"x\": \"合肥\",\n" +
                "    \"y\": 18,\n" +
                "    \"s\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"x\": \"杭州\",\n" +
                "    \"y\": 14,\n" +
                "    \"s\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"x\": \"济南\",\n" +
                "    \"y\": 11,\n" +
                "    \"s\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"x\": \"成都\",\n" +
                "    \"y\": 9,\n" +
                "    \"s\": \"1\"\n" +
                "  }\n" +
                "]";
        JSONArray jsonArray = JSONObject.parseArray(str);
        List<Data> dataList = new ArrayList<>();
        Data data = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            data = new Data();
            JSONObject json = jsonArray.getJSONObject(i);
            data.setArea(json.getString("x"));
            data.setValue(Integer.parseInt(json.getString("y")));
            data.setS(json.getString("s"));
            dataList.add(data);
        }
        return dataList;
    }

}
