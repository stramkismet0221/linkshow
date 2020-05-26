package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.ThirdPay;
import com.ddzhuan.manage.service.YpThidPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/22 15:53
 */
@Controller
@RequestMapping("/pay")
public class ThirdPayController {

    @Autowired
    private YpThidPayService ypThidPayService;

    @RequestMapping("tosetthirdpay")
    public String toSetThirdPay(Model model){
        List<ThirdPay> thirdPays = ypThidPayService.getThirdPayList();
        model.addAttribute("thirdPays",thirdPays);
        return "thirdpay/thirdpayset";
    }


    @RequestMapping("setthirdpaytype")
    @ResponseBody
    public BaseResultInfo setThirdPayType(@RequestParam String payUrl,
                                          @RequestParam String serverUrl,
                                          @RequestParam String signKey,
                                          @RequestParam(name = "payTypes",required = false) List<String> payTypes,
                                          HttpServletRequest request){
        BaseResultInfo result = ypThidPayService.setThirdPay(payUrl, serverUrl, signKey, payTypes, request);
        return result;
    }


}
