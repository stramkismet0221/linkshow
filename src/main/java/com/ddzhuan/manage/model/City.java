package com.ddzhuan.manage.model;

import java.io.Serializable;

public class City implements Serializable {

    private Long cityId;
    private Long provinceId;
    private String cityName;
    private String areaCode;
    private Long code;
    private String name;
    private Long cityLevel;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(Long cityLevel) {
        this.cityLevel = cityLevel;
    }
}
