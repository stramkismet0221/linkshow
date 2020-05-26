package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.Coupon;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/30 11:33
 */
public interface CouponService {

    /**
     *
     * 分页查询抵扣券/折扣券
     * @param coupon 条件
     * @param type 1：活动列表，2：历史活动列表
     * @param pagination 分页信息
     * @return 抵扣券/折扣券列表
     */
    List<Coupon> queryCouponListByConditions(Coupon coupon, Integer type, Pagination pagination);

    /**
     * 获取抵扣券/折扣券 信息
     * @param couponId 抵扣券/折扣券ID
     * @return 抵扣券/折扣券 信息
     */
    Coupon queryCouponById(Long couponId);

    /**
     * 新增抵扣券/折扣券
     * @param coupon 抵扣券/折扣券
     * @return 是否成功
     */
    Boolean insertCoupon(Coupon coupon);

    /**
     * 编辑抵扣券/折扣券
     * @param coupon 抵扣券/折扣券
     * @return 是否成功
     */
    Boolean modifyCoupon(Coupon coupon);

}
