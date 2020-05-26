package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/30 11:06
 */
public interface CouponDao {

    List<Coupon> queryCouponListByConditions(@Param("coupon")Coupon coupon, @Param("type") Integer type,
                                             @Param("start")Integer start, @Param("end")Integer end);

    int countCouponByConditions(@Param("coupon")Coupon coupon, @Param("type") Integer type);

    int insertCouPon(Coupon couPon);

    int modifyCoupon(Coupon couPon);

    Coupon queryCouponById(Long couponId);
}
