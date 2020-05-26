package com.ddzhuan.manage.model.jumi.shop;

import java.util.Date;

public class Shop {
    private Long id;
    private String shopno; //门店编号
    private String shopName; //店铺名称
    private Long phone;//联系电话
    private String tenantid;//所属商户id
    private Date beginTime;//营业开始时间
    private Date endTime;//营业结束时间
    private Long provinceId;//所在省
    private Long cityId;//所在市
    private Long districtId;//所在县
    private String email;//邮箱
    private String address;//地址
    private String customerphone;//客服电话
    private String memo;//店铺介绍
    private Long state;//状态（1 正常营业  0 暂停营业） 默认1

    private Long facilityNumber; //设备数量
    private Long shopType;//店铺类型

    private Date createTime; //创建时间
    private Date updateTime; //修改时间


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getShopno() {
        return shopno;
    }

    public void setShopno(String shopno) {
        this.shopno = shopno;
    }

    public Long getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityNumber(Long facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    public Long getShopType() {
        return shopType;
    }

    public void setShopType(Long shopType) {
        this.shopType = shopType;
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
