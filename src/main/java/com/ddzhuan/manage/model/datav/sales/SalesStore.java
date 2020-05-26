package com.ddzhuan.manage.model.datav.sales;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺销售额
 *
 * @author likeke
 * @date 2019/10/30
 */
public class SalesStore implements Serializable {

    private static final long serialVersionUID = -5711952701311651299L;
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
     * 店铺id
     */
    private String storeId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 销售额
     */
    private String sales;
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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
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
