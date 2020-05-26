package com.ddzhuan.manage.service.datav;

import com.ddzhuan.manage.model.datav.sales.*;

import java.util.List;

/**
 * 销售额
 * @author likeke
 * @date 2019/10/30
 */
public interface SalesService {

    /**
     * 获取总销售额、同比
     * @param oId 运营商id
     * @return
     */
    SalesTotal getSalesTotal(String oId);

    /**
     * datav定时任务更新
     * @param id id
     * @param total 总额
     * @param compared 同比
     */
    void modifySalesTotal(Long id, Double total, Integer compared);

    /**
     * 店铺销售额
     * @param oId 运营商id
     * @return
     */
    List<SalesStore> getSalesStoreList(String oId);

    /**
     * 季度销售信息
     * @param oId
     * @param year
     * @param quarter
     * @return
     */
    SalesQuarter getSalesQuarter(String oId, String year, String quarter);

    /**
     * 区域销售信息
     * @param oId 运营商id
     * @param type 1、销售额 2、利润 3、销售额最大记录 4、利润最大记录
     * @param size  查询记录数
     * @return
     */
    List<SalesArea> getSalesAreaList(String oId, Integer type, Integer size);

    /**
     * 商品（药品）销售排行榜
     * @param oId
     * @param size
     * @return
     */
    List<SalesProduct> getSalesProductList(String oId, Integer size);

    /**
     * 品类销售占比
     * @param oId
     * @param size
     * @param categoryName
     * @return
     */
    List<SalesCategory> getSalesCategoryList(String oId, Integer size, String categoryName);

    /**
     * 用户年龄段统计
     * @param oId
     * @param type
     * @return
     */
    List<SalesUserAge> getSalesUserAgeList(String oId, Integer type);

    /**
     * 年销售额增长率
     * @param oId
     * @return
     */
    List<SalesYear> getSalesYearList(String oId);

    /**
     * 销售目标
     * @param oId
     * @param type
     * @return
     */
    List<SalesTarget> getSalesTargetList(String oId, Integer type);
}
