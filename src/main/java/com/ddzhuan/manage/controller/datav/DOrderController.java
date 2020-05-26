package com.ddzhuan.manage.controller.datav;

import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.datav.DOrder;
import com.ddzhuan.manage.service.datav.DOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单controller
 */
@Controller
@RequestMapping("datav")
public class DOrderController extends BaseController {

    @Autowired
    private DOrderService dOrderService;

    private static final int START_OF_ARRAY = 2;

    private static final int END_OF_ARRAY = 11;

    @RequestMapping("orderSave")
    @ResponseBody
    public BaseResultInfo saveOrder(HttpServletRequest request, HttpServletResponse response,
                                    Model model) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            for (int i = START_OF_ARRAY; i < END_OF_ARRAY; i++) {
                DOrder order = new DOrder();
                order.setId(Long.parseLong(String.valueOf(i)));
                order.setProductId("1");
                order.setProductName("阿莫西林胶囊");
                order.setCategoryId("1");
                order.setCategoryName("消炎药");
                order.setStoreId("1");
                order.setStoreName("徐汇店1");
                order.setoId("1");
                order.setoName("国大药房");
                order.setAreaId("1");
                order.setAreaName("徐汇区");
                order.setUserId("1000"+String.valueOf(i));
                order.setUserName("购买者00"+String.valueOf(i));
                order.setUserSex(1);
                order.setUserAge(29);
                order.setUserType(1);
                order.setCostPrice(19.90);
                order.setOriginalPrice(18.80);
                order.setDiscount(0d);
                order.setPrice(19.90);
                order.setProfit(1.10);
                order.setStatus(1);
                dOrderService.saveOrder(order);
            }
            result.setSuccess(true);
            result.setMsg("保存成功");
        } catch (Exception ex) {
            result.setMsg("保存失败");
            log.error(ex.getMessage(), ex);
        }
        return result;
    }
}
