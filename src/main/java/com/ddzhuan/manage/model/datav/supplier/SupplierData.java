package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.List;

/**
 * 供应商数据分析
 *
 * @author likeke
 * @date 2019/09/02
 */
public class SupplierData implements Serializable {

    private static final long serialVersionUID = -4509870718806229247L;

    /**
     * 标题
     */
    private String title;
    /**
     * 模块编号
     */
    private String no;
    /**
     * 供应商列表
     */
    private List<Supplier> suppliers;
    /**
     * 新增供应商数量
     */
    private Integer growthNum;
    /**
     * 同比增长
     */
    private Integer growthRate;

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

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Integer getGrowthNum() {
        return growthNum;
    }

    public void setGrowthNum(Integer growthNum) {
        this.growthNum = growthNum;
    }

    public Integer getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Integer growthRate) {
        this.growthRate = growthRate;
    }
}
