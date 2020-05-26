package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员按月消费额
 *
 * @author likeke
 * @date 2019/10/29
 */
public class MemberMConsume implements Serializable {

    private static final long serialVersionUID = -3370269855253215998L;
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
     * 月份
     */
    private String month;
    /**
     * 消费额
     */
    private Double sales;
    /**
     * 累计
     */
    private Double total;
    /**
     * 累计占比
     */
    private Double proportion;
    /**
     * 类型 1：按月统计 2：累计占比
     */
    private Integer type;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
