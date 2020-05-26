package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Coupon;
import com.ddzhuan.manage.model.YpProduct;
import com.ddzhuan.manage.model.YpTerminal;
import com.ddzhuan.manage.service.CouponService;
import com.ddzhuan.manage.service.YpProductService;
import com.ddzhuan.manage.service.YpTerminalService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/30 12:15
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private CouponService couponService;

    @Autowired
    private YpTerminalService ypTerminalService;

    @Autowired
    private YpProductService ypProductService;

    /**
     * 主页面
     */
    @RequestMapping("getcouponlist")
    public String mainPage(HttpServletRequest request, HttpServletResponse response, Model model,
                           @RequestParam(required = false, defaultValue = "1") Integer type) {
        model.addAttribute("type", type);
        return "coupon/couponlist";
    }

    /**
     * 分页 活动列表、历史活动列表
     *
     * @param coupon     条件
     * @param pagination 分页信息
     * @param type       1：活动列表 2、历史活动列表（默认为1）
     * @return
     */
    @RequestMapping("couponactlist")
    public String getCouponList(Model model, Coupon coupon, Pagination pagination,
                                @RequestParam(required = false, defaultValue = "1") Integer type) {
        List<Coupon> couponList = couponService.queryCouponListByConditions(coupon, type, pagination);
        model.addAttribute("couponList", couponList);
        model.addAttribute("coupon", coupon);
        model.addAttribute("type", type);
        String view = "coupon/couponactlist";
        if (type.intValue() == 2) {
            view = "coupon/couponacthislist";
        }
        return view;
    }

    /**
     * 跳转新增抵扣券页面
     */
    @RequestMapping("toadd")
    public String toAdd() {
        return "coupon/addcoupon";
    }

    /**
     * 修改
     */
    @RequestMapping("getcouponbyid")
    public String toModify(Model model, Long couponId, Integer type) {
        Coupon coupon = couponService.queryCouponById(couponId);
        model.addAttribute("coupon", coupon);
        return "coupon/modifycoupon";
    }

    /**
     * 添加 抵扣券,折扣券
     *
     * @param coupon 条件
     * @return
     */
    @RequestMapping("insertcoupon")
    @ResponseBody
    public BaseResultInfo insertCoupon(Coupon coupon) {
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            Boolean flag = couponService.insertCoupon(coupon);
            resultInfo.setSuccess(flag);
            resultInfo.setMsg("添加成功");
        } catch (Exception e) {
            log.error(e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("添加失败");
        }
        return resultInfo;
    }


    /**
     * 更新 抵扣券,折扣券
     *
     * @param coupon 条件
     * @return
     */
    @RequestMapping("modifycoupon")
    @ResponseBody
    public BaseResultInfo modifyCoupon(Coupon coupon) {
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            Boolean flag = couponService.modifyCoupon(coupon);
            resultInfo.setSuccess(flag);
            resultInfo.setMsg("更新成功");
        } catch (Exception e) {
            log.error(e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("更新失败");
        }
        return resultInfo;
    }


    /**
     * 更新 抵扣券,折扣券
     *
     * @param coupon 条件
     * @return
     */
    @RequestMapping("updatestatus")
    @ResponseBody
    public BaseResultInfo delCouponById(Coupon coupon) {
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            Boolean flag = couponService.modifyCoupon(coupon);
            resultInfo.setSuccess(flag);
            resultInfo.setMsg("删除成功");
        } catch (Exception e) {
            log.error(e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("删除失败");
        }
        return resultInfo;
    }

    /**
     * 详情
     * @param model
     * @param couponId
     * @return
     */
    @RequestMapping("coupondetail")
    public String couponDetail(Model model, Long couponId) {
        Coupon coupon = couponService.queryCouponById(couponId);
        model.addAttribute("coupon", coupon);
        return "coupon/coupondetail";
    }

    /**
     * 获取设备列表
     *
     * @param terminalIds 已选择的设备列表
     * @return
     */
    @RequestMapping("getmachinelist")
    @ResponseBody
    public BaseResultInfo getMachineList(@RequestParam(required = false) String terminalIds) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            List<YpTerminal> allTerminalList = ypTerminalService.getAllYpTerminalList();
            List<YpTerminal> unSelect = new ArrayList<>();
            List<YpTerminal> select = new ArrayList<>();
            if (!StringUtils.isEmpty(terminalIds)) {
                for (YpTerminal terminal : allTerminalList) {
                    if (terminalIds.indexOf(terminal.getId()) > -1) {
                        select.add(terminal);
                    } else {
                        unSelect.add(terminal);
                    }
                }
            } else {
                unSelect = allTerminalList;
            }

            HashMap<String, Object> terminalMap = new HashMap<>(2);
            terminalMap.put("unSelect", unSelect);
            terminalMap.put("select", select);
            result.setSuccess(true);
            result.setMsg("获取设备列表成功");
            result.setExtInfo(terminalMap);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("获取设备列表失败");
        }
        return result;
    }

    /**
     * 获取所有商品
     *
     * @return modal
     */
    @RequestMapping("getproductlist")
    public String getProductList(Model model, Pagination pagination,
                                 @RequestParam(required = false) String keyword,
                                 String barCodes) {
        pagination.setRows(5);
        List<YpProduct> products = ypProductService.getYpProductListByConditions(null, keyword, pagination);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("barCodes", barCodes);
        return "coupon/productlist";
    }


}
