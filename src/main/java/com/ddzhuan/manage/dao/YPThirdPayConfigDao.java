package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.YPThirdPayConfig;

/**
 * @author jiang yong tao
 * @date 2019/7/22 17:51
 */
public interface YPThirdPayConfigDao {

    /**
     * 保存支付配置信息
     * @param ypThirdPayConfig 支付配置
     * @return
     */
    int insertYpThirdPay(YPThirdPayConfig ypThirdPayConfig);

    /**
     *
     * 查询支付配置count
     * @return
     */
    int countYpThirdPay();

    /**
     *
     * 更新支付配置信息
     * @param ypThirdPayConfig 支付配置
     * @return
     */
    int updateYpThirdPay(YPThirdPayConfig ypThirdPayConfig);

}
