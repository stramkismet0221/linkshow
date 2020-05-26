package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员店铺销售额比例
 *
 * @author likeke
 * @date 2019/10/29
 */
public class MemberStoreSalesPro implements Serializable {

    private static final long serialVersionUID = 7999051531520742455L;
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
     * 店铺总销售额
     */
    private Double totalSales;
    /**
     * 会员销售额
     */
    private String memberSales;
    /**
     * 会员销售额比例
     */
    private String proportion;
    /**
     * 图标url
     */
    private String iconUrl;
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

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public String getMemberSales() {
        return memberSales;
    }

    public void setMemberSales(String memberSales) {
        this.memberSales = memberSales;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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

