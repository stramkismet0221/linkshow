package com.ddzhuan.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.model.YpProductCategory;
import com.ddzhuan.manage.model.YpProduct;
import com.ddzhuan.manage.service.SysLogInfoService;
import com.ddzhuan.manage.service.YpProductService;
import com.ddzhuan.manage.tool.yopoint.YoPointApi;
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
import java.util.stream.Collectors;

/**
 * 友朋商品管理
 *
 * @author likeke
 * @date 2019/07/15
 */
@Controller
@RequestMapping("/product/")
public class ProductController extends BaseController {

    @Autowired
    private SysLogInfoService sysLogInfoService;

    @Autowired
    private YpProductService ypProductService;

    /**
     * 获取商品分类树列表
     * @return 商品分类树
     */
    @RequestMapping("getcategorylist")
    public String getProductCategoryList(HttpServletRequest request, HttpServletResponse response,
                                         Model model) {
        // TODO 暂时只做保存入库操作
//        saveCategoryList();
        return null;
    }

    /**
     * 获取商品列表
     * @param pagination 分页
     * @param keyword 商品名称或商品条形码关键字搜索
     * @return 商品列表界面
     */
    @RequestMapping("getproductlist")
    public String getOperatorList(HttpServletRequest request, HttpServletResponse response, Model model,
                                  Pagination pagination, @RequestParam(required = false) String keyword,
                                  YpProduct ypProduct) {
        List<YpProduct> products = ypProductService.getYpProductListByConditions(ypProduct, keyword, pagination);

        // 根据已有的商品，获取已有商品种类，不做全部展示
        List<YpProduct> operatorList = ypProductService.getYpProductListByConditions(new YpProduct(), null, new Pagination(1, 2147483647));
        List<String> categoryIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(operatorList)) {
            categoryIds = operatorList.stream().map(YpProduct::getCategoryId).collect(Collectors.toList());
        }
        // 商品如果为空，商品分类也是空
        List<YpProductCategory> categoryList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categoryIds)) {
            List<YpProductCategory> categories = ypProductService.getYpCategoryList(categoryIds);
            categoryList = convertProductCategory(categories);
        }
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("ypProduct", ypProduct);
        return "product/productlist";
    }

    /**
     * 新增商品页面
     */
    @RequestMapping("addproduct")
    public String addOperator(HttpServletRequest request, HttpServletResponse response,
                              Model model) {
        List<YpProductCategory> categories = ypProductService.getYpCategoryList(null);
        List<YpProductCategory> categoryList = convertProductCategory(categories);
        model.addAttribute("categoryList", categoryList);
        return "product/addproduct";
    }

    /**
     * 保存
     */
    @RequestMapping("saveproduct")
    @ResponseBody
    public BaseResultInfo saveOperator(HttpServletRequest request, HttpServletResponse response,
                                       Model model, YpProduct product) {
        // TODO 调友朋接口，保存商品，返回json字符串，存一份到自己数据库
        if (StringUtils.isEmpty(product.getBarCode())) {
            product.setBarCode(getRandomNumberByNum(13));
        }
        BaseResultInfo result = new BaseResultInfo();
        // 校验是否条形码在数据库中是否存在
        String barCode = product.getBarCode();
        YpProduct ypProduct = ypProductService.getYpProductByBarCode(barCode);
        if (ypProduct != null) {
            result.setSuccess(false);
            result.setMsg("条形码已存在，请更换");
            return result;
        }
        try {
            ypProductService.saveYpProduct(product);
            result.setSuccess(true);
            result.setMsg("保存成功");
            User user = (User) request.getSession().getAttribute("sysuser");
            sysLogInfoService.addSysLogInfoForTask(1,String.format("%s%s%s%s","操作员:",user.getUserName()," 添加商品数据为：",product.toString()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("保存失败");
        }
        return result;
    }

    @RequestMapping("removeproduct")
    @ResponseBody
    public BaseResultInfo removeOperator(HttpServletRequest request, HttpServletResponse response,
                                         Model model, String barCode) {
        User user = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            YpProduct ypProduct = ypProductService.getYpProductByBarCode(barCode);
            ypProductService.removeProductByBarCode(barCode);
            result.setSuccess(true);
            result.setMsg("删除成功");
            sysLogInfoService.addSysLogInfoForTask(1,String.format("%s%s%s%s", "操作员:", user.getUserName(), " 删除商品数据：",ypProduct.toString()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 调用友朋接口获取数据并保存
     */
    private void saveCategoryList() {
        YoPointApi api = new YoPointApi();
        String jsonStr = api.getProductCategoryList();
        JSONObject result = JSONObject.parseObject(jsonStr);
        JSONArray categoryJsonArray = JSONObject.parseArray(result.getString("data"));
        List<YpProductCategory> categories = new ArrayList<>();
        YpProductCategory category = null;
        for (int i = 0; i < categoryJsonArray.size(); i++) {
            JSONObject job = categoryJsonArray.getJSONObject(i);
            category = new YpProductCategory();
            category.setId(job.getString("id"));
            category.setName(job.getString("Name"));
            category.setPid(job.getString("ParentID"));
            category.setLevels(job.getIntValue("Level"));
            categories.add(category);
            JSONArray childJsonArr1 = JSONObject.parseArray(job.getString("child"));
            if (childJsonArr1.size() > 0) {
                for (int j = 0; j < childJsonArr1.size(); j++) {
                    JSONObject job2 = childJsonArr1.getJSONObject(j);
                    category = new YpProductCategory();
                    category.setId(job2.getString("id"));
                    category.setName(job2.getString("Name"));
                    category.setPid(job2.getString("ParentID"));
                    category.setLevels(job2.getIntValue("Level"));
                    categories.add(category);
                    // TODO 商品种类三级没有数据，暂时解析到二级，后面再做修改
                }
            }
        }
        log.info("友朋商品种类批量入库开始。。。。。。");
        // 批量入库
        if (!CollectionUtils.isEmpty(categories)) {
            ypProductService.batchSaveYpCategory(categories);
        }
        log.info("友朋商品种类批量入库结束，共计入库记录数："+categories.size());
    }

    /**
     * 转化为分级结构
     * @param categories 商品种类列表
     * @return 商品种类分级列表
     */
    private List<YpProductCategory> convertProductCategory(List<YpProductCategory> categories) {
        List<String> pids = categories.stream().map(YpProductCategory::getPid).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(pids)) {
            return new ArrayList<>();
        }
        for (int i = pids.size() - 1; i >= 0; i--) {
            if (StringUtils.isEmpty(pids.get(i))) {
                pids.remove(i);
            }
        }
        categories.addAll(ypProductService.getYpCategoryList(pids));
        List<YpProductCategory> categoryList = new ArrayList<>();
        for (YpProductCategory category : categories) {
            if (category.getLevels().intValue() == 1) {
                categoryList.add(category);
            }
        }
        for (YpProductCategory categoryLevel1 : categoryList) {
            List<YpProductCategory> categoryListLevel1 = new ArrayList<>();
            for (YpProductCategory category : categories) {
                if (category.getLevels().intValue() == 1) {
                    continue;
                }
                if (categoryLevel1.getId().equals(category.getPid())) {
                    categoryListLevel1.add(category);
                }
            }
            categoryLevel1.setChild(categoryListLevel1);
        }
        return categoryList;
    }

    private static String getRandomNumberByNum(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            long randomNum = Math.round(Math.floor(Math.random() * 10.0D));
            sb.append(randomNum);
        }
        return sb.toString();
    }

}
