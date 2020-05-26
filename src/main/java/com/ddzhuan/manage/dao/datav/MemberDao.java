package com.ddzhuan.manage.dao.datav;

import com.ddzhuan.manage.model.datav.member.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员
 *
 * @author likeke
 * @date 2019/10/28
 */
public interface MemberDao {

    /**
     * 根据type查询会员统计列表
     * @param type 1.今年按月统计 2.去年按月统计 3.总的
     * @return 会员统计列表
     */
    List<MemberTotal> queryMemberTotalList(@Param("type") Integer type);

    /**
     * 根据type查询会员增长统计列表
     * @param type
     * @return
     */
    List<MemberGrowth> queryMemberGrowthList(@Param("type") Integer type);

    /**
     * 根据type查询LinkBay会员增长统计列表
     * @param type
     * @return
     */
    List<MemberLBGrowth> queryMemberLBGrowthList(@Param("type") Integer type);

    /**
     * 会员销售额统计
     * @param type
     * @return
     */
    List<MemberConsume> queryMemberConsumeList(@Param("type") Integer type);

    /**
     * LinkBay会员销售额统计
     * @param type
     * @return
     */
    List<MemberLBConsume> queryMemberLBConsumeList(@Param("type") Integer type);

    /**
     * 会员消费额按月统计
     * @param type
     * @return
     */
    List<MemberMConsume> queryMemberMConsumeList(@Param("type") Integer type);

    /**
     * LinkBay带来的利润
     * @param type
     * @return
     */
    List<MemberLBProfit> queryMemberLBProfitList(@Param("type") Integer type);

    /**
     * 区域会员销售额
     * @return
     */
    List<MemberAreaSales> queryMemberAreaSalesList();

    /**
     * 店铺会员销售情况
     * @return
     */
    List<MemberStoreSales> queryMemberStoreSalesList();

    /**
     * 店铺会员销售额比例
     * @return
     */
    List<MemberStoreSalesPro> queryMemberStoreSalesProList();
}
