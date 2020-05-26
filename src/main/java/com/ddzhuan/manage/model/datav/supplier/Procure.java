package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.List;

/**
 * 采购
 *
 * @author likeke
 * @date 2019/09/02
 */
public class Procure implements Serializable {

    private static final long serialVersionUID = 150291831663211753L;
    /**
     * 采购总额
     */
    private Long total;
    /**
     * 采购同比
     */
    private Integer compared;
    /**
     * 供应商供应排名
     */
    private List<Supplier> suppliers;
    /**
     * 季度采购进度
     */
    private QuarterData quarterData;
    /**
     * 省市采购额排名
     */
    private AreaData areaData;
    /**
     * 品类采购排名
     */
    private CategoryData categoryData;
    /**
     * 产品采购排名
     */
    private ProductData productData;
    /**
     * 采购额占比饼状图
     */
    private ProductRankData productRankData;
    /**
     * 供应商占比饼状图
     */
    private SupplierData supplierData;
    /**
     * 采购额增速
     */
    private List<YearData> yearData;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCompared() {
        return compared;
    }

    public void setCompared(Integer compared) {
        this.compared = compared;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public QuarterData getQuarterData() {
        return quarterData;
    }

    public void setQuarterData(QuarterData quarterData) {
        this.quarterData = quarterData;
    }

    public AreaData getAreaData() {
        return areaData;
    }

    public void setAreaData(AreaData areaData) {
        this.areaData = areaData;
    }

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public ProductRankData getProductRankData() {
        return productRankData;
    }

    public void setProductRankData(ProductRankData productRankData) {
        this.productRankData = productRankData;
    }

    public SupplierData getSupplierData() {
        return supplierData;
    }

    public void setSupplierData(SupplierData supplierData) {
        this.supplierData = supplierData;
    }

    public List<YearData> getYearData() {
        return yearData;
    }

    public void setYearData(List<YearData> yearData) {
        this.yearData = yearData;
    }
}
