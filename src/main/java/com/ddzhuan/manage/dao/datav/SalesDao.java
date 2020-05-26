package com.ddzhuan.manage.dao.datav;

import com.ddzhuan.manage.model.datav.sales.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售额
 *
 * @author likeke
 * @date 2019/10/30
 */
public interface SalesDao {

    /**
     * 获取总销售额、同比
     *
     * @param oId 运营商id
     * @return
     */
    SalesTotal querySalesTotal(@Param("oId") String oId);

    /**
     * datav定时任务更新
     *
     * @param id       id
     * @param total    总额
     * @param compared 同比
     */
    void updateSalesTotal(@Param("id") Long id, @Param("total") Double total, @Param("compared") Integer compared);

    /**
     * 店铺销售额
     *
     * @param oId 运营商id
     * @return
     */
    List<SalesStore> querySalesStoreList(@Param("oId") String oId);

    /**
     * 季度销售信息
     *
     * @param oId
     * @param year
     * @param quarter
     * @return
     */
    SalesQuarter querySalesQuarter(@Param("oId") String oId, @Param("year") String year, @Param("quarter") String quarter);

    /**
     * 区域销售信息
     *
     * @param oId  运营商id
     * @param type 1、销售额 2、利润 3、销售额最大记录 4、利润最大记录
     * @param size 查询长度
     * @return
     */
    List<SalesArea> querySalesAreaList(@Param("oId") String oId, @Param("type") Integer type, @Param("size") Integer size);

    /**
     * 商品（药品）销售排行榜
     *
     * @param oId
     * @param size
     * @return
     */
    List<SalesProduct> querySalesProductList(@Param("oId") String oId, @Param("size") Integer size);

    /**
     * 品类销售占比
     *
     * @param oId
     * @param size
     * @param categoryName
     * @return
     */
    List<SalesCategory> querySalesCategoryList(@Param("oId") String oId, @Param("size") Integer size, @Param("categoryName") String categoryName);

    /**
     * 用户年龄段统计
     *
     * @param oId
     * @param type
     * @return
     */
    List<SalesUserAge> querySalesUserAgeList(@Param("oId") String oId, @Param("type") Integer type);

    /**
     * 年度销售额增长率
     *
     * @param oId
     * @return
     */
    List<SalesYear> querySalesYearList(@Param("oId") String oId);

    /**
     * 销售目标
     *
     * @param oId
     * @param type
     * @return
     */
    List<SalesTarget> querySalesTargetList(@Param("oId") String oId, @Param("type") Integer type);
}
