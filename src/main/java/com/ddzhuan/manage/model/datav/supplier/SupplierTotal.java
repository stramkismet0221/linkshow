package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商采购总额
 *
 * @author likeke
 * @date 2019/10/23
 */
public class SupplierTotal implements Serializable {

    private static final long serialVersionUID = 5332773606978385038L;
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
     * 采购总额
     */
    private Long total;
    /**
     * 采购同比
     */
    private Integer compared;
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

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

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
