package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺会员销售
 *
 * @author likeke
 * @date 2019/10/29
 */
public class MemberStoreSales implements Serializable {

    private static final long serialVersionUID = 14329404826327386L;
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
     * 去年销售额
     */
    private Double lastYearSales;
    /**
     * 今年销售额
     */
    private String nowYearSales;
    /**
     * 增长率
     */
    private String growth;
    /**
     * 增长/下降图标
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

    public Double getLastYearSales() {
        return lastYearSales;
    }

    public void setLastYearSales(Double lastYearSales) {
        this.lastYearSales = lastYearSales;
    }

    public String getNowYearSales() {
        return nowYearSales;
    }

    public void setNowYearSales(String nowYearSales) {
        this.nowYearSales = nowYearSales;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
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
