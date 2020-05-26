package com.ddzhuan.manage.model.datav.sales;

import java.io.Serializable;
import java.util.Date;

/**
 * 区域销售
 *
 * @author likeke
 * @date 2019/10/30
 */
public class SalesArea implements Serializable {

    private static final long serialVersionUID = -6124214597026596690L;
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
     * 区域code
     */
    private String areaCode;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 销售额
     */
    private Long sales;
    /**
     * 利润
     */
    private Long profit;
    /**
     * 销售额增长率
     */
    private Double sGrowth;
    /**
     * 利润增长率
     */
    private Double pGrowth;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public Double getSGrowth() {
        return sGrowth;
    }

    public void setSGrowth(Double sGrowth) {
        this.sGrowth = sGrowth;
    }

    public Double getPGrowth() {
        return pGrowth;
    }

    public void setPGrowth(Double pGrowth) {
        this.pGrowth = pGrowth;
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
