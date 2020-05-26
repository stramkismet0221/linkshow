package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 按设备统计销售额
 *
 * @author likeke
 * @date 2019/09/10
 */
public class OrderTerminal implements Serializable {

    private static final long serialVersionUID = -234825482719648882L;

    /**
     * id（业务id）
     */
    private Long id;
    /**
     * 设备id
     */
    private String tId;
    /**
     * 设备名称
     */
    private String tName;
    /**
     * 销售额
     */
    private Double sales;
    /**
     * 成本价
     */
    private Double originalCost;
    /**
     * 利润
     */
    private Double profit;
    /**
     * 已结算金额
     */
    private Double settled;
    /**
     * 统计时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Double originalCost) {
        this.originalCost = originalCost;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Double getSettled() {
        return settled;
    }

    public void setSettled(Double settled) {
        this.settled = settled;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
