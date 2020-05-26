package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 抵扣券/折扣券
 * @author jiang yong tao
 * @date 2019/7/30 10:54
 */
public class Coupon implements Serializable {

    private static final long serialVersionUID = 537650541655131773L;

    private Long id;

    /** 抵扣券（折扣券、优惠券）名称 */
    private String name;

    /** 优惠类型（1、抵扣券 2、折扣券） */
    private Integer couponType;

    /** 抵扣券/折扣券值  */
    private Double discount;

    /** 折扣券/抵扣券 数量  */
    private Integer amount;

    /** 兑换时间类型【1、领取后（单位：天）2、截至时间（时间格式）】 */
    private Integer exTimeType;

    /** 领取后天数  */
    private Integer afterReceive;

    /** 截至时间  */
    private Date cutoffTime;

    private String cutoffTimeStr;

    /** 可兑换商品（商品条形码表示，多个以","隔开，全部用"ALL"表示）*/
    private String barCodes;

    /** 可兑换设备（设备id表示，多个以","隔开，全部用“ALL”表示） */
    private String terminals;

    /** 状态（0：删除 1：正常 2：暂停） */
    private Integer status;

    private Date createTime;

    private Date modifyTime;

    /** 已领取数 */
    private Long receivedNum;

    /** 已兑换数 */
    private Long exchangedNum;


    private List<YpProduct> ypProducts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getExTimeType() {
        return exTimeType;
    }

    public void setExTimeType(Integer exTimeType) {
        this.exTimeType = exTimeType;
    }

    public Integer getAfterReceive() {
        return afterReceive;
    }

    public void setAfterReceive(Integer afterReceive) {
        this.afterReceive = afterReceive;
    }

    public Date getCutoffTime() {
        return cutoffTime;
    }

    public void setCutoffTime(Date cutoffTime) {
        this.cutoffTime = cutoffTime;
    }

    public String getBarCodes() {
        return barCodes;
    }

    public void setBarCodes(String barCodes) {
        this.barCodes = barCodes;
    }

    public String getTerminals() {
        return terminals;
    }

    public void setTerminals(String terminals) {
        this.terminals = terminals;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
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

    public Long getReceivedNum() {
        return receivedNum;
    }

    public void setReceivedNum(Long receivedNum) {
        this.receivedNum = receivedNum;
    }

    public Long getExchangedNum() {
        return exchangedNum;
    }

    public void setExchangedNum(Long exchangedNum) {
        this.exchangedNum = exchangedNum;
    }

    public List<YpProduct> getYpProducts() {
        return ypProducts;
    }

    public String getCutoffTimeStr() {
        return cutoffTimeStr;
    }

    public void setCutoffTimeStr(String cutoffTimeStr) {
        this.cutoffTimeStr = cutoffTimeStr;
    }

    public void setYpProducts(List<YpProduct> ypProducts) {
        this.ypProducts = ypProducts;
    }
}
