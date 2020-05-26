package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.List;

/**
 * 产品采购额占比数据分析
 *
 * @author likeke
 * @date 2019/09/02
 */
public class ProductRankData implements Serializable {

    private static final long serialVersionUID = -4512796853956453828L;

    /**
     * 标题
     */
    private String title;
    /**
     * 模块编号
     */
    private String no;
    /**
     *
     */
    private List<ProductInfo> productInfos;
    /**
     * 最大占比产品名称
     */
    private String maxRankProductName;
    /**
     * 最大占比比值（单位：%）
     */
    private Double maxRankValue;
    /**
     * 最小占比产品名称
     */
    private String minRankProductName;
    /**
     * 最小占比比值（单位：%）
     */
    private Double minRankValue;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public String getMaxRankProductName() {
        return maxRankProductName;
    }

    public void setMaxRankProductName(String maxRankProductName) {
        this.maxRankProductName = maxRankProductName;
    }

    public Double getMaxRankValue() {
        return maxRankValue;
    }

    public void setMaxRankValue(Double maxRankValue) {
        this.maxRankValue = maxRankValue;
    }

    public String getMinRankProductName() {
        return minRankProductName;
    }

    public void setMinRankProductName(String minRankProductName) {
        this.minRankProductName = minRankProductName;
    }

    public Double getMinRankValue() {
        return minRankValue;
    }

    public void setMinRankValue(Double minRankValue) {
        this.minRankValue = minRankValue;
    }
}
