package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商采购额信息
 *
 * @author likeke
 * @date 2019/10/23
 */
public class SupplierRank implements Serializable {

    private static final long serialVersionUID = 5853183670417748492L;
    /**
     * id
     */
    private Long id;
    /**
     * 供应商id
     */
    private String sId;
    /**
     * 供应商名称
     */
    private String sName;
    /**
     * 采购金额
     */
    private String amount;
    /**
     * 排名
     */
    private Integer rank;
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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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
