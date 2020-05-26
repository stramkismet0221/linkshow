package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.enums.SysLogInfoEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.ThirdPayDao;
import com.ddzhuan.manage.dao.YPThirdPayConfigDao;
import com.ddzhuan.manage.model.ThirdPay;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.model.YPThirdPayConfig;
import com.ddzhuan.manage.service.SysLogInfoService;
import com.ddzhuan.manage.service.YpThidPayService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/22 10:41
 */
@Service
public class YpThirdPayServiceImpl implements YpThidPayService {

    @Autowired
    private ThirdPayDao thirdPayDao;

    @Autowired
    private YPThirdPayConfigDao thirdPayConfigDao;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    @Override
    public List<ThirdPay> getThirdPayList() {
        return thirdPayDao.queryThirdPayList();
    }

    @Override
    public void addThirdPay(YPThirdPayConfig ypThirdPayConfig) {
        thirdPayConfigDao.insertYpThirdPay(ypThirdPayConfig);
    }

    @Override
    public void updateThirdPay(YPThirdPayConfig ypThirdPayConfig) {
        thirdPayConfigDao.updateYpThirdPay(ypThirdPayConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResultInfo setThirdPay(String payUrl, String serverUrl, String singKey, List<String> payType, HttpServletRequest request) {
        BaseResultInfo result = new BaseResultInfo();
        try {

            // 通过友朋接口配置第三方支付信息
//            YoPointApi api = new YoPointApi();
//            String value = api.setThirdPay(payUrl,serverUrl,singKey,payType);
//            Map<String,Object> jsonObj = JSON.parseObject(value);
//            //error_code = 0 成功, error_code = -1 失败
//            if (Objects.nonNull(jsonObj) && Objects.nonNull(jsonObj.get("error_code")) && jsonObj.get("error_code").equals(0)){
//                result.setSuccess(true);
//                result.setMsg("配置成功");
//            }
//
//            if (Objects.nonNull(jsonObj) && Objects.nonNull(jsonObj.get("error_code")) && jsonObj.get("error_code").equals(-1)){
//                result.setSuccess(false);
//                result.setMsg("配置失败");
//            }


            YPThirdPayConfig thirdpay = new YPThirdPayConfig();
            thirdpay.setPayUrl(payUrl);
            thirdpay.setServerUrl(serverUrl);
            thirdpay.setSignKey(singKey);
            thirdpay.setPayType(StringUtils.join(payType,","));
            thirdpay.setStatus(0);
//            thirdpay.setStatus(Integer.parseInt(jsonObj.get("error_code").toString()));

            int count = thirdPayConfigDao.countYpThirdPay();
            if (count > 0){
                updateThirdPay(thirdpay);
            }else {
                addThirdPay(thirdpay);
            }

            User user = (User)request.getSession().getAttribute("sysuser");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),"操作员"+user.getUserName()+"配置第三方支付信息为"+thirdpay.toString());
            result.setSuccess(true);
            result.setMsg("配置成功");

        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("配置失败");
        }
        return result;
    }
}
