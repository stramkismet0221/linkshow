package com.ddzhuan.manage.controller.datav;

import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.datav.supplier.*;
import com.ddzhuan.manage.service.datav.SupplierService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * datav 数据大屏，可视化界面统计api--供应商数据统计
 *
 * @author likeke
 * @date 2019/09/03
 */
@Controller
@RequestMapping("/datav/")
public class SupplierController extends BaseController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 供应商相关视图，根据method不同，调用不同接口
     * @param method 接口名
     * @return 重定向
     */
    @RequestMapping("supplier")
    public String supplier(HttpServletRequest request, HttpServletResponse response,
                           Model model, String method, @RequestParam(required = false) Integer type) {
        String redirect = type==null?String.format("redirect:%s", method):String.format("redirect:%s?type=%s", method, type);
        return redirect;
    }

    @RequestMapping("total")
    @ResponseBody
    public Object getTotal(HttpServletRequest request, HttpServletResponse response, Model model) {
        SupplierTotal supplierTotal = supplierService.getTotalByOId("1");
        Procure procure = new Procure();
        procure.setTotal(supplierTotal.getTotal());
        procure.setCompared(supplierTotal.getCompared());
        List<Procure> procures = new ArrayList<>();
        procures.add(procure);
        return procures;
    }

    @RequestMapping("rank")
    @ResponseBody
    public Object getRank(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<SupplierRank> supplierRankList = supplierService.getSupplierRankList("1", 13);
        List<Supplier> suppliers = new ArrayList<>();
        fillSuppliers(suppliers, supplierRankList);
        return suppliers;
    }

    @RequestMapping("quarter")
    @ResponseBody
    public Object getQuarter(HttpServletRequest request, HttpServletResponse response, Model model) {
        SupplierQuarter quarter = supplierService.getSupplierQuarter("1", "2019", "1");
        QuarterData quarterData = new QuarterData();
        quarterData.setTitle("一季度采购进度");
        quarterData.setNo("01");
        quarterData.setPercent(quarter.getPercent());
        quarterData.setAmount(quarter.getAmount());
        quarterData.setGrowth(quarter.getGrowth());
        List<QuarterData> list = new ArrayList<>();
        list.add(quarterData);
        return list;
    }

    @RequestMapping("area")
    @ResponseBody
    public Object getArea(HttpServletRequest request, HttpServletResponse response, Model model,
                          @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<SupplierArea> supplierAreaList = supplierService.getSupplierAreaList("1", 8, type);
            return fillAreaInfo(supplierAreaList);
        }
        List<SupplierArea> supplierAreas = supplierService.getSupplierAreaList("1", 1, type);
        SupplierArea supplierArea = new SupplierArea();
        if (!CollectionUtils.isEmpty(supplierAreas) && supplierAreas.size() == 1) {
            supplierArea = supplierAreas.get(0);
        }
        AreaData areaData = new AreaData();
        areaData.setTitle("省市采购额排名");
        areaData.setNo("02");
        areaData.setMaxGrowthArea(supplierArea.getAreaName());
        areaData.setMaxGrowthValue(supplierArea.getGrowth());
        List<AreaData> list = new ArrayList<>();
        list.add(areaData);
        return list;
    }

    @RequestMapping("category")
    @ResponseBody
    public Object getCategory(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            return fillCategoryInfo();
        }
        CategoryData categoryData = new CategoryData();
        categoryData.setTitle("品类排名");
        categoryData.setNo("03");
        categoryData.setMaxGroupCategory("感冒");
        categoryData.setMaxGroupValue(44.60);
        List<CategoryData> list = new ArrayList<>();
        list.add(categoryData);
        return list;
    }

    @RequestMapping("product")
    @ResponseBody
    public Object getProduct(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            return fillProductInfo();
        }
        ProductData productData = new ProductData();
        productData.setTitle("产品采购额排名");
        productData.setNo("04");
        List<ProductData> list = new ArrayList<>();
        list.add(productData);
        return list;
    }

    @RequestMapping("productrank")
    @ResponseBody
    public Object getProductRank(HttpServletRequest request, HttpServletResponse response, Model model,
                                 @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            return fillProductInfo();
        }
        ProductRankData productRankData = new ProductRankData();
        productRankData.setTitle("产品采购额占比");
        productRankData.setNo("05");
        productRankData.setMaxRankProductName("三九感冒灵");
        productRankData.setMaxRankValue(0.1);
        productRankData.setMinRankProductName("21金维他");
        productRankData.setMinRankValue(0.09);
        List<ProductRankData> list = new ArrayList<>();
        list.add(productRankData);
        return list;
    }

    @RequestMapping("info")
    @ResponseBody
    public Object getInfo(HttpServletRequest request, HttpServletResponse response, Model model,
                          @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            return fillSupplier();
        }
        SupplierData supplierData = new SupplierData();
        supplierData.setTitle("供应商占比");
        supplierData.setNo("06");
        supplierData.setGrowthNum(33);
        supplierData.setGrowthRate(12);
        List<SupplierData> list = new ArrayList<>();
        list.add(supplierData);
        return list;
    }

    @RequestMapping("year")
    @ResponseBody
    public Object getYear(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<YearData> yearData = new ArrayList<>();
        fillYearData(yearData);
        return yearData;
    }

    @RequestMapping("target")
    @ResponseBody
    public Object getTarget(HttpServletRequest request, HttpServletResponse response, Model model,
                            @RequestParam(required = false) Integer type) {
        Map<String, String> map = new HashMap<>();
        if (type != null && type == 1) {
            map.put("企业年度采购额", "4354万元");
            map.put("企业季度采购额", "1325万元");
        }
        if (type != null && type == 2) {
            map.put("企业库存比", "20%");
            map.put("存货周转率", "4");
        }
        if (type != null && type == 3) {
            map.put("库存销售比率", "12%");
            map.put("目标账期", "80天");
        }
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        return list;
    }

    private void fillYearData(List<YearData> yearData) {
        YearData y1 = new YearData();
        y1.setYear("2015");
        y1.setGrowthValue(2.5);
        yearData.add(y1);
        YearData y2 = new YearData();
        y2.setYear("2016");
        y2.setGrowthValue(2.5);
        yearData.add(y2);
        YearData y3 = new YearData();
        y3.setYear("2017");
        y3.setGrowthValue(2.1);
        yearData.add(y3);
        YearData y4 = new YearData();
        y4.setYear("2018");
        y4.setGrowthValue(2.6);
        yearData.add(y4);
        YearData y5 = new YearData();
        y5.setYear("2019");
        y5.setGrowthValue(3.4);
        yearData.add(y5);
    }

    private List<Supplier> fillSupplier() {
        List<Supplier> suppliers = new ArrayList<>();
        Supplier s1 = new Supplier();
        s1.setName("华北制药");
        s1.setAmount(30d);
        suppliers.add(s1);
        Supplier s2 = new Supplier();
        s2.setName("上海医药");
        s2.setAmount(25d);
        suppliers.add(s2);
        Supplier s3 = new Supplier();
        s3.setName("新华制药");
        s3.setAmount(7d);
        suppliers.add(s3);
        Supplier s4 = new Supplier();
        s4.setName("施贵宝");
        s4.setAmount(22d);
        Supplier s5 = new Supplier();
        s5.setName("华润三九");
        s5.setAmount(17d);
        return suppliers;
    }

    private List<ProductInfo> fillProductInfo() {
        List<ProductInfo> productInfos = new ArrayList<>();
        ProductInfo p1 = new ProductInfo();
        p1.setName("三九感冒灵");
        p1.setNumber(29);
        p1.setS("1");
        productInfos.add(p1);
        ProductInfo p2 = new ProductInfo();
        p2.setName("维生素C");
        p2.setNumber(27);
        p2.setS("1");
        productInfos.add(p2);
        ProductInfo p3 = new ProductInfo();
        p3.setName("鸿茅药酒");
        p3.setNumber(23);
        p3.setS("1");
        productInfos.add(p3);
        ProductInfo p4 = new ProductInfo();
        p4.setName("润洁滴眼液");
        p4.setNumber(20);
        p4.setS("1");
        productInfos.add(p4);
        ProductInfo p5 = new ProductInfo();
        p5.setName("水仙风油精");
        p5.setNumber(18);
        p5.setS("1");
        productInfos.add(p5);
        ProductInfo p6 = new ProductInfo();
        p6.setName("龙虎清凉油");
        p6.setNumber(14);
        p6.setS("1");
        productInfos.add(p6);
        ProductInfo p7 = new ProductInfo();
        p7.setName("21金维他");
        p7.setNumber(11);
        p7.setS("1");
        productInfos.add(p7);
        ProductInfo p8 = new ProductInfo();
        p8.setName("三九胃泰");
        p8.setNumber(9);
        p8.setS("1");
        productInfos.add(p8);
        ProductInfo p9 = new ProductInfo();
        p9.setName("奇正贴膏");
        p9.setNumber(8);
        p9.setS("1");
        productInfos.add(p9);
        ProductInfo p10 = new ProductInfo();
        p10.setName("虎标贴膏");
        p10.setNumber(5);
        p10.setS("1");
        productInfos.add(p10);
        return productInfos;
    }

    private List<AreaInfo> fillAreaInfo(List<SupplierArea> supplierAreaList) {
        if (CollectionUtils.isEmpty(supplierAreaList)) {
            return new ArrayList<>();
        }
        List<AreaInfo> areaInfos = new ArrayList<>();
        AreaInfo areaInfo = null;
        for (SupplierArea supplierArea : supplierAreaList) {
            areaInfo = new AreaInfo();
            areaInfo.setName(supplierArea.getAreaName());
            areaInfo.setNumber(supplierArea.getAmount());
            areaInfo.setS("1");
            areaInfos.add(areaInfo);
        }
        return areaInfos;
    }

    private List<AreaInfo> fillCategoryInfo() {
        List<AreaInfo> areaInfos = new ArrayList<>();
        AreaInfo a1 = new AreaInfo();
        a1.setName("五官");
        a1.setNumber(29);
        a1.setS("1");
        areaInfos.add(a1);
        AreaInfo a2 = new AreaInfo();
        a2.setName("感冒");
        a2.setNumber(27);
        a2.setS("1");
        areaInfos.add(a2);
        AreaInfo a3 = new AreaInfo();
        a3.setName("维生素");
        a3.setNumber(23);
        a3.setS("1");
        areaInfos.add(a3);
        AreaInfo a4 = new AreaInfo();
        a4.setName("药酒");
        a4.setNumber(20);
        a4.setS("1");
        areaInfos.add(a4);
        AreaInfo a5 = new AreaInfo();
        a5.setName("心血管");
        a5.setNumber(18);
        a5.setS("1");
        areaInfos.add(a5);
        AreaInfo a6 = new AreaInfo();
        a6.setName("皮肤");
        a6.setNumber(14);
        a6.setS("1");
        areaInfos.add(a6);
        AreaInfo a7 = new AreaInfo();
        a7.setName("消化");
        a7.setNumber(11);
        a7.setS("1");
        areaInfos.add(a7);
        AreaInfo a8 = new AreaInfo();
        a8.setName("中药");
        a8.setNumber(9);
        a8.setS("1");
        areaInfos.add(a8);
        return areaInfos;
    }

    private void fillSuppliers(List<Supplier> suppliers, List<SupplierRank> supplierRankList) {
        Supplier s = null;
        for (SupplierRank supplierRank : supplierRankList) {
            s = new Supplier();
            s.setName(supplierRank.getsName());
            s.setValue(supplierRank.getAmount());
            suppliers.add(s);
        }
    }

}
