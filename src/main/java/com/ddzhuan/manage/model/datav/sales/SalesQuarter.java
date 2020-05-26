package com.ddzhuan.manage.model.datav.sales;

import java.io.Serializable;
import java.util.Date;

/**
 * 季度销售额
 *
 * @author likeke
 * @date 2019/10/30
 */
public class SalesQuarter implements Serializable {

    private static final long serialVersionUID = 961038443426421082L;
    /**
     * id
     */
    private Long id;
    /**
     * 运营商id
     */
    private String oId;
    /**
     * 运营商名称
     */
    private String oName;
    /**
     * 年度
     */
    private String year;
    /**
     * 季度
     */
    private String quarter;
    /**
     * 销售额
     */
    private Double sales;
    /**
     * 占比
     */
    private Double percent;
    /**
     * 增长率
     */
    private Double growth;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOId() {
        return oId;
    }

    public void setOId(String oId) {
        this.oId = oId;
    }

    public String getOName() {
        return oName;
    }

    public void setOName(String oName) {
        this.oName = oName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
