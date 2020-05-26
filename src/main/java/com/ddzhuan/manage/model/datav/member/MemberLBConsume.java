package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;
import java.util.Date;

/**
 * LinkBay销售额
 *
 * @author likeke
 * @date 2019/10/28
 */
public class MemberLBConsume implements Serializable {

    private static final long serialVersionUID = 5644761778813560505L;
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
     * 时间字符串
     */
    private String timeStr;
    /**
     * 销售额
     */
    private Double sales;
    /**
     * 同比
     */
    private Double tCompare;
    /**
     * 环比
     */
    private Double hCompare;
    /**
     * 类型：1、实线 2、虚线 3、总额 4、同比 5、环比
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


    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double gettCompare() {
        return tCompare;
    }

    public void settCompare(Double tCompare) {
        this.tCompare = tCompare;
    }

    public Double gethCompare() {
        return hCompare;
    }

    public void sethCompare(Double hCompare) {
        this.hCompare = hCompare;
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
