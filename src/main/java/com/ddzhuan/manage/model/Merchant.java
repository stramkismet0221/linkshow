package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;

public class Merchant implements Serializable {

    private Long id;

    private String company;
    private String description;
    private String licenseImg;
    private Date createTime;
    private Date modifyTime;
    private Integer locked;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
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

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                ", licenseImg='" + licenseImg + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", locked=" + locked +
                ", status=" + status +
                '}';
    }
}
