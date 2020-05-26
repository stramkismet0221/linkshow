package com.ddzhuan.manage.model;

import java.io.Serializable;

/**
 * 友朋设备场地
 *
 * @author likeke
 * @date 2016/06/20
 */
public class YpTerminalPlace implements Serializable{

    private static final long serialVersionUID = 4671118471640873457L;
    /**
     * 场地id
     */
    private String id;
    /**
     * 场地名称
     */
    private String name;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 场地主体
     */
    private String subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
