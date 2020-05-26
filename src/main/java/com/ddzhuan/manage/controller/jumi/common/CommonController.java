package com.ddzhuan.manage.controller.jumi.common;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.jumi.drp.JmGoodsUnit;
import com.ddzhuan.manage.model.jumi.product.Goods;
import com.ddzhuan.manage.model.jumi.product.GoodsSelectFields;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;
import com.ddzhuan.manage.service.jumi.GoodsService;
import com.ddzhuan.manage.service.jumi.JmGoodsTypeService;
import com.ddzhuan.manage.service.jumi.JmGoodsUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jumi/common/")
public class CommonController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private JmGoodsTypeService jmGoodsTypeService;

    @Autowired
    private JmGoodsUnitService jmGoodsUnitService;

    @RequestMapping("getgoodslist")
    public String getGoodsList(HttpServletRequest request, HttpServletResponse response, Model model,
                               Pagination pagination, @RequestParam(required = false) String keyword,
                               @RequestParam(required = false) Long goodsTypeId) {
        pagination.setRows(5);
        GoodsSelectFields goodsSelectFields = new GoodsSelectFields();
        goodsSelectFields.setKeyword(keyword);
        goodsSelectFields.setGoodsTypeId(goodsTypeId);
        List<Goods> goodsList = goodsService.getGoodsInfoList(goodsSelectFields, null, pagination);
        fillGoodsUnitName(goodsList);
        List<JmGoodsType> goodsTypes = jmGoodsTypeService.pageJmGoodsType(new JmGoodsType(), new Pagination(1, Integer.MAX_VALUE));
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("goodsTypeId", goodsTypeId);
        model.addAttribute("goodsTypes", goodsTypes);
        return "jumi/common/goodslist";
    }

    private void fillGoodsUnitName(List<Goods> goodsList) {
        if (CollectionUtils.isEmpty(goodsList)) {
            return;
        }
        List<JmGoodsUnit> units = jmGoodsUnitService.getAllGoodsUnitList();
        Map<Long, JmGoodsUnit> map = new HashMap<>();
        for (JmGoodsUnit unit : units) {
            map.put(unit.getId(), unit);
        }
        for (Goods goods : goodsList) {
            Long unitId = goods.getUnitId();
            if (map.get(unitId) != null) {
                goods.setUnitName(map.get(unitId).getName());
            }
        }
    }
}
