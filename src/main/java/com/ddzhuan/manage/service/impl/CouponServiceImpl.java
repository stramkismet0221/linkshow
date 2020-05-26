package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.DateFormat;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.CouponDao;
import com.ddzhuan.manage.dao.YpProductDao;
import com.ddzhuan.manage.model.Coupon;
import com.ddzhuan.manage.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author jiang yong tao
 * @date 2019/7/30 11:33
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private YpProductDao ypProductDao;

    @Override
    public List<Coupon> queryCouponListByConditions(Coupon coupon, Integer type, Pagination pagination) {
        int couponCount = couponDao.countCouponByConditions(coupon, type);
        pagination.setTotal(couponCount);
        List<Coupon> coupons = couponDao.queryCouponListByConditions(coupon, type, pagination.getStart(), pagination.getEnd());
        return coupons;
    }

    @Override
    public Coupon queryCouponById(Long couponId) {
        Assert.isTrue(Objects.nonNull(couponId), "抵扣券/折扣券ID不能为空");
        Coupon coupon = couponDao.queryCouponById(couponId);
        if (Objects.nonNull(coupon.getBarCodes())){
            List<String> barCodes = Arrays.asList(coupon.getBarCodes().split(","));
            coupon.setYpProducts(ypProductDao.queryYpProductListByBarCodes(barCodes));
        }
        return coupon;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertCoupon(Coupon coupon) {

        if (Objects.nonNull(coupon.getCutoffTimeStr())){
            coupon.setCutoffTime(DateFormat.Stringdate(coupon.getCutoffTimeStr()));
        }
        return couponDao.insertCouPon(coupon) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean modifyCoupon(Coupon coupon) {

        return couponDao.modifyCoupon(coupon) == 1;
    }

}
