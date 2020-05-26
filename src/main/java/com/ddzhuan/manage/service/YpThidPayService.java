package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.ThirdPay;
import com.ddzhuan.manage.model.YPThirdPayConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/22 10:41
 */
public interface YpThidPayService {

    List<ThirdPay> getThirdPayList();

    /**
     * 保存支付配置信息
     * @param ypThirdPayConfig 支付配置
     */
    void addThirdPay(YPThirdPayConfig ypThirdPayConfig);

    /**
     * 更新支付配置信息
     * @param ypThirdPayConfig 支付配置
     */
    void updateThirdPay(YPThirdPayConfig ypThirdPayConfig);

    /**
     * 设置支付配置信息
     * @param payUrl    支付跳转地址
     * @param serverUrl  第三方服务端API地址
     * @param singKey    签名密钥
     * @param subPayType  支付渠道
     * @return
     */
    BaseResultInfo setThirdPay(String payUrl, String serverUrl, String singKey, List<String> subPayType, HttpServletRequest request);

}
