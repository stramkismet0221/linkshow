package com.ddzhuan.manage.controller.jumi.drp;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.jumi.drp.Warehouse;
import com.ddzhuan.manage.model.jumi.drp.WarnSet;
import com.ddzhuan.manage.model.jumi.drp.WarnSetRel;
import com.ddzhuan.manage.model.jumi.product.Goods;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;
import com.ddzhuan.manage.service.jumi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 预警设置Controller
 *
 * @author likeke
 * @date 2020/01/10
 */
@Controller
@RequestMapping("/jumi/drp/warnset/")
public class WareWarnSetController extends BaseController {

    @Autowired
    private WareWarnSetService wareWarnSetService;

    @Autowired
    private JmGoodsTypeService jmGoodsTypeService;

    @Autowired
    private WarehouseService warehouseService;

    /**
     * 预警设置页面
     */
    @RequestMapping("addwarnset")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        WarnSet warnSet = wareWarnSetService.getWareWarnSetById(null);
        model.addAttribute("warnSet", warnSet);
        if (warnSet.getSelType() != null && warnSet.getSelType() == 2) {
            String getGoodsTypeNames = getGoodsTypeNames(warnSet);
            model.addAttribute("getGoodsTypeNames", getGoodsTypeNames);
        } else if (warnSet.getSelType() != null && warnSet.getSelType() == 3) {
            String goodsNames = getGoodsNames(warnSet);
            model.addAttribute("goodsNames", goodsNames);
        }
        return "jumi/drp/warewarnset/index";
    }

    /**
     * 保存、更新，已存在既更新，不存在既保存
     *
     * @param warnSet
     * @return
     */
    @RequestMapping("savewarnset")
    @ResponseBody
    public BaseResultInfo saveWarnSet(HttpServletRequest request, HttpServletResponse response,
                                      Model model, WarnSet warnSet) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            wareWarnSetService.saveWareWarnSet(warnSet);
            result.setSuccess(true);
            result.setMsg("保存成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("保存异常");
        }
        return result;
    }

    /**
     * 获取商品分类列表
     */
    @RequestMapping("getgoodstypes")
    public String getGoodsTypes(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<JmGoodsType> jmGoodsTypes = jmGoodsTypeService.pageJmGoodsType(new JmGoodsType(), new Pagination(1, Integer.MAX_VALUE));
        String goodsTypeIds = "";
        WarnSet warnSet = wareWarnSetService.getWareWarnSetById(null);
        if (warnSet.getId() == null || warnSet.getSelType() == 2) {
            goodsTypeIds = warnSet.getGoodsTypeIds();
            fillGoodsTypeChecked(goodsTypeIds, jmGoodsTypes);
        }
        model.addAttribute("jmGoodsTypes", jmGoodsTypes);
        model.addAttribute("goodsTypeIds", goodsTypeIds);
        return "/jumi/drp/warewarnset/goodstypes";
    }

    /**
     * 获取仓库列表
     */
    @RequestMapping("getwarehouses")
    public String getWareHouses(HttpServletRequest request, HttpServletResponse response, Model model,
                                @RequestParam(required = false) Long warnSetId) {
        List<Warehouse> wareHouses = warehouseService.getWarehouseList("", new Pagination(1, Integer.MAX_VALUE));
        List<WarnSetRel> warnSetRels = new ArrayList<>();
        if (warnSetId != null) {
            warnSetRels = wareWarnSetService.getRelListByWarnSetId(warnSetId);
            fillWareHouseStock(wareHouses, warnSetRels);
        }
        model.addAttribute("wareHouses", wareHouses);
        return "/jumi/drp/warewarnset/warehouses";
    }

    private String getGoodsTypeNames(WarnSet warnSet) {
        List<JmGoodsType> jmGoodsTypes = warnSet.getJmGoodsTypeList();
        if (CollectionUtils.isEmpty(warnSet.getJmGoodsTypeList())) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jmGoodsTypes.size(); i++) {
            JmGoodsType jmGoodsType = jmGoodsTypes.get(i);
            if (i == jmGoodsTypes.size()-1) {
                sb.append(jmGoodsType.getName());
            } else {
                sb.append(jmGoodsType.getName()).append(";");
            }
        }
        return sb.toString();
    }

    private String getGoodsNames(WarnSet warnSet) {
        List<Goods> goodsList = warnSet.getGoodsList();
        if (CollectionUtils.isEmpty(warnSet.getGoodsList())) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < goodsList.size(); i++) {
            Goods goods = goodsList.get(i);
            if (i == goodsList.size()-1) {
                sb.append(goods.getName());
            } else {
                sb.append(goods.getName()).append(";");
            }
        }
        return sb.toString();
    }

    private void fillWareHouseStock(List<Warehouse> wareHouses, List<WarnSetRel> warnSetRels) {
        if (CollectionUtils.isEmpty(wareHouses) || CollectionUtils.isEmpty(warnSetRels)) {
            return;
        }
        for (Warehouse warehouse : wareHouses) {
            for (WarnSetRel warnSetRel : warnSetRels) {
                if (warehouse.getId().equals(warnSetRel.getWareId())) {
                    warehouse.setMinStock(warnSetRel.getMinStock());
                    warehouse.setMaxStock(warnSetRel.getMaxStock());
                }
            }
        }
    }

    private void fillGoodsTypeChecked(String goodsTypeIds, List<JmGoodsType> jmGoodsTypes) {
        if (StringUtils.isEmpty(goodsTypeIds)) {
            return;
        }
        if (CollectionUtils.isEmpty(jmGoodsTypes)) {
            return;
        }
        List<String> goodsTypeIdList = Arrays.asList(goodsTypeIds.split(","));
        for (JmGoodsType jmGoodsType : jmGoodsTypes) {
            if (goodsTypeIdList.contains(String.valueOf(jmGoodsType.getId()))) {
                jmGoodsType.setChecked(true);
                fillGoodsTypeChecked(goodsTypeIds,jmGoodsType.getChildren());
            }
        }
    }

}
