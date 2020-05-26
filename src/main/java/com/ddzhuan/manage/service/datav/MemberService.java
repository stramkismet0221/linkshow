package com.ddzhuan.manage.service.datav;

import com.ddzhuan.manage.model.datav.member.*;

import java.util.List;

/**
 * 会员
 *
 * @author likeke
 * @date 2019/10/28
 */
public interface MemberService {

    /**
     * 根据type查询会员统计列表
     * @param type 1.今年按月统计 2.去年按月统计 3.总的
     * @return 会员统计列表
     */
    List<MemberTotal> getMemberTotalList(Integer type);

    /**
     * 根据type查询会员增长统计列表
     * @param type
     * @return
     */
    List<MemberGrowth> getMemberGrowthList(Integer type);

    /**
     * 根据type查询LinkBay会员增长统计列表
     * @param type
     * @return
     */
    List<MemberLBGrowth> getMemberLBGrowthList(Integer type);

    /**
     * 会员销售额统计
     * @param type
     * @return
     */
    List<MemberConsume> getMemberConsumeList(Integer type);

    /**
     * LinkBay会员销售额统计
     * @param type
     * @return
     */
    List<MemberLBConsume> getMemberLBConsumeList(Integer type);

    /**
     * 会员消费额按月统计
     * @param type
     * @return
     */
    List<MemberMConsume> getMemberMConsumeList(Integer type);

    /**
     * LinkBay带来的利润
     * @param type
     * @return
     */
    List<MemberLBProfit> getMemberLBProfitList(Integer type);

    /**
     * 区域会员销售额
     * @return
     */
    List<MemberAreaSales> getMemberAreaSalesList();

    /**
     * 店铺会员销售情况
     * @return
     */
    List<MemberStoreSales> getMemberStoreSalesList();

    /**
     * 店铺会员销售额比例
     * @return
     */
    List<MemberStoreSalesPro> getMemberStoreSalesProList();
}
