package com.ddzhuan.manage.model.jumi.drp;

import java.io.Serializable;
import java.util.Date;

public class Warehouse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8656219811359618212L;
	private Long id;
    private String name;
    private String code; //仓库编号
    private Long status; //状态  1 开启  2关闭
    private Long isFirst; //是否为首选仓库：1：是   0：否
    private String storage; //存储条件
    private Long provinceId;
    private Long cityId;
    private Long districtId;
    private String detailAddress; //详细地址
    private Date createTime;
    private Date updateTime;
    private Integer minStock;
    private Integer maxStock;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Long isFirst) {
        this.isFirst = isFirst;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
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

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }
}
