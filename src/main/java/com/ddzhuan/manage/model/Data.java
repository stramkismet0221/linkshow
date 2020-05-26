package com.ddzhuan.manage.model;

import java.io.Serializable;


public class Data implements Serializable {

    private static final long serialVersionUID = 838994172145706859L;

    private String area;

    private Integer value;

    private String s;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
