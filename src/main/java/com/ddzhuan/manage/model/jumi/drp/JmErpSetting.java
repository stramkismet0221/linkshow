package com.ddzhuan.manage.model.jumi.drp;

import java.io.Serializable;
import java.util.Date;

public class JmErpSetting implements Serializable {

    private static final long serialVersionUID = -7850540601941412777L;

    private Long id;
    private Integer batchmng;
    private Integer fillValidityDay;
    private Integer needAudit;
    private Integer priceValueType;
    private Date createTime;
    private Date updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBatchmng() {
        return batchmng;
    }

    public void setBatchmng(Integer batchmng) {
        this.batchmng = batchmng;
    }

    public Integer getFillValidityDay() {
        return fillValidityDay;
    }

    public void setFillValidityDay(Integer fillValidityDay) {
        this.fillValidityDay = fillValidityDay;
    }

    public Integer getNeedAudit() {
        return needAudit;
    }

    public void setNeedAudit(Integer needAudit) {
        this.needAudit = needAudit;
    }

    public Integer getPriceValueType() {
        return priceValueType;
    }

    public void setPriceValueType(Integer priceValueType) {
        this.priceValueType = priceValueType;
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
