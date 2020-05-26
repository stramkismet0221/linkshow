package com.ddzhuan.manage.service.datav;

import com.ddzhuan.manage.model.datav.supplier.SupplierArea;
import com.ddzhuan.manage.model.datav.supplier.SupplierQuarter;
import com.ddzhuan.manage.model.datav.supplier.SupplierRank;
import com.ddzhuan.manage.model.datav.supplier.SupplierTotal;

import java.util.List;

/**
 * 运营商采购相关信息统计
 *
 * @author likeke
 * @date 2019/10/23
 */
public interface SupplierService {

    /**
     * 通过运营商查看采购总额、同比
     * @param oId 运营商id
     * @return 采购总额等信息
     */
    SupplierTotal getTotalByOId(String oId);

    /**
     * 获取供应商列表（按采购金额、排名）
     * @param oId 运营商id
     * @param size 列表长度
     * @return 供应商信息列表
     */
    List<SupplierRank> getSupplierRankList(String oId, Integer size);

    /**
     * 根据季度获取季度采购相关信息
     * @param oId 运营商id
     * @param year 年度
     * @param quarter 季度
     * @return 季度采购相关信息
     */
    SupplierQuarter getSupplierQuarter(String oId, String year, String quarter);

    /**
     * 按地区统计采购相关
     * @param oId 运营商id
     * @param size 列表长度
     * @param type 1、列表 2、增长率最快的区域
     * @return 地区采购信息列表
     */
    List<SupplierArea> getSupplierAreaList(String oId, Integer size, Integer type);

}
