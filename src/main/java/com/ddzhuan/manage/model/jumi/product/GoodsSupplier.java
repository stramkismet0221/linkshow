package com.ddzhuan.manage.model.jumi.product;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 供货商
 */
public class GoodsSupplier implements Serializable {

    private static final long serialVersionUID = 8915607956458400978L;
    private Long id;
    private String name; //供货商名称
    private Integer states;//状态 1：启用 0：禁用
    private String contacts;  //联系人
    private String telephone; //联系电话
    private String email;     //邮箱
    private String mngmodel;  //经营模式
    private String paymentType; //结款方式
    private String deliveryType; //送货方式
    private Double deliveryMoney; //配送费
    private Double returnPoint; //固定返点
    private Double deliveryPoint; //配送返点
    private Double taxPoint; //发票税点
    private String bankInfo; //结算银行账户信息
    private String invoiceInfo; //开票信息
    private String contactAddress; //联系地址
    private String description; //其他描述
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

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

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMngmodel() {
        return mngmodel;
    }

    public void setMngmodel(String mngmodel) {
        this.mngmodel = mngmodel;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Double getDeliveryMoney() {
        return deliveryMoney;
    }

    public void setDeliveryMoney(Double deliveryMoney) {
        this.deliveryMoney = deliveryMoney;
    }

    public Double getReturnPoint() {
        return returnPoint;
    }

    public void setReturnPoint(Double returnPoint) {
        this.returnPoint = returnPoint;
    }

    public Double getDeliveryPoint() {
        return deliveryPoint;
    }

    public void setDeliveryPoint(Double deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    public Double getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(Double taxPoint) {
        this.taxPoint = taxPoint;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
