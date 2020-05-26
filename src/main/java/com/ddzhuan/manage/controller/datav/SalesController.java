package com.ddzhuan.manage.controller.datav;

import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.datav.sales.*;
import com.ddzhuan.manage.service.datav.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售额
 *
 * @author likeke
 * @date 2019/10/30
 */
@Controller
@RequestMapping("/datav/")
public class SalesController extends BaseController {

    @Autowired
    private SalesService salesService;

    /**
     * 总销售额
     */
    @RequestMapping("stotal")
    @ResponseBody
    public Object getSalesTotal(HttpServletRequest request, HttpServletResponse response,
                                Model model, @RequestParam(required = false, defaultValue = "1") String oId) {
        SalesTotal total = salesService.getSalesTotal(oId);
        List<SalesTotal> totals = new ArrayList<>();
        totals.add(total);
        return totals;
    }

    /**
     * 药店销售额排名
     */
    @RequestMapping("sstorerank")
    @ResponseBody
    public Object getStoreSalesRank(HttpServletRequest request, HttpServletResponse response,
                                    Model model, @RequestParam(required = false, defaultValue = "1") String oId) {
        List<SalesStore> salesStores = salesService.getSalesStoreList(oId);
        return salesStores;
    }

    /**
     * 季度销售额
     */
    @RequestMapping("squarter")
    @ResponseBody
    public Object getSalesQuarter(HttpServletRequest request, HttpServletResponse response,
                                  Model model, @RequestParam(required = false, defaultValue = "1") String oId,
                                  @RequestParam(required = false, defaultValue = "2019") String year,
                                  @RequestParam(required = false, defaultValue = "1") String quarter) {
        SalesQuarter salesQuarter = salesService.getSalesQuarter(oId, year, quarter);
        List<SalesQuarter> salesQuarters = new ArrayList<>();
        salesQuarters.add(salesQuarter);
        return salesQuarters;
    }

    /**
     * 区域销售额
     */
    @RequestMapping("sareasales")
    @ResponseBody
    public Object getSalesArea(HttpServletRequest request, HttpServletResponse response,
                               Model model, @RequestParam(required = false, defaultValue = "1") String oId,
                               @RequestParam(required = false, defaultValue = "1") Integer type,
                               @RequestParam(required = false, defaultValue = "8") Integer size) {
        List<SalesArea> salesAreas = null;
        if (type != null && type == 1) {
            salesAreas = salesService.getSalesAreaList(oId, 1, size);
        } else {
            salesAreas = salesService.getSalesAreaList(oId, 3, 1);
        }
        return salesAreas;
    }

    /**
     * 区域利润
     */
    @RequestMapping("sareaprofit")
    @ResponseBody
    public Object getAreaProfit(HttpServletRequest request, HttpServletResponse response,
                                Model model, @RequestParam(required = false, defaultValue = "1") String oId,
                                @RequestParam(required = false, defaultValue = "1") Integer type,
                                @RequestParam(required = false, defaultValue = "8") Integer size) {
        List<SalesArea> salesAreas = null;
        if (type != null && type == 1) {
            salesAreas = salesService.getSalesAreaList(oId, 2, size);
        } else {
            salesAreas = salesService.getSalesAreaList(oId, 4, 1);
        }
        return salesAreas;
    }

    /**
     * 药品销售额排行榜
     */
    @RequestMapping("sproduct")
    @ResponseBody
    public Object getSalesProduct(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(required = false, defaultValue = "1") String oId,
                                  @RequestParam(required = false, defaultValue = "10") Integer size) {
        List<SalesProduct> salesProducts = salesService.getSalesProductList(oId, size);
        return salesProducts;
    }

    /**
     * 品类销售排行榜
     */
    @RequestMapping("scategory")
    @ResponseBody
    public Object getSalesCategory(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false, defaultValue = "1") String oId,
                                   @RequestParam(required = false, defaultValue = "1") Integer type) {
        List<SalesCategory> salesCategories = null;
        if (type != null && type == 1) {
            salesCategories = salesService.getSalesCategoryList(oId, 5, null);
        } else if (type != null && type == 2) {
            salesCategories = salesService.getSalesCategoryList(oId, 1, "感冒类");
        } else if (type != null && type == 3) {
            salesCategories = salesService.getSalesCategoryList(oId, 1, "皮肤类");
        }
        return salesCategories;
    }

    /**
     * 客户年龄段统计
     */
    @RequestMapping("suserage")
    @ResponseBody
    public List<SalesUserAge> getSalesUserAge(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(required = false, defaultValue = "1") String oId,
                                  @RequestParam(required = false, defaultValue = "1") Integer type) {
        List<SalesUserAge> salesUserAges = salesService.getSalesUserAgeList(oId, type);
        return salesUserAges;
    }

    /**
     * 年度销售额增长率
     */
    @RequestMapping("syear")
    @ResponseBody
    public List<SalesYear> getSalesYear(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false, defaultValue = "1") String oId) {
        List<SalesYear> salesYears = salesService.getSalesYearList(oId);
        return salesYears;
    }

    /**
     * 销售目标
     */
    @RequestMapping("starget")
    @ResponseBody
    public Object getTarget(HttpServletRequest request, HttpServletResponse response, Model model,
                            @RequestParam(required = false, defaultValue = "1") String oId,
                            @RequestParam(required = false, defaultValue = "1") Integer type) {
        List<SalesTarget> salesTargets = salesService.getSalesTargetList(oId, type);
        String targetName1 = "";
        String targetValue1 = "";
        String targetName2 = "";
        String targetValue2 = "";
        Map<String, String> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(salesTargets) && salesTargets.size()>=1) {
            targetName1 = salesTargets.get(0).getTargetName();
            targetValue1 = salesTargets.get(0).getTargetValue();
            map.put(targetName1, targetValue1);
        }
        if (!CollectionUtils.isEmpty(salesTargets) && salesTargets.size()>=2) {
            targetName2 = salesTargets.get(1).getTargetName();
            targetValue2 = salesTargets.get(1).getTargetValue();
            map.put(targetName2, targetValue2);
        }
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        return list;
    }
}
