package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;

/**
 * @author likeke
 */
public class StoreMember implements Serializable {

    private static final long serialVersionUID = -7514762318766302646L;
    /**
     * 区域
     */
    private String area;
    /**
     * 销售额
     */
    private Double pv;
    /**
     *
     */
    private String series1;
    /**
     *
     */
    private String series2;
    /**
     * attribute
     */
    private String attribute;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getPv() {
        return pv;
    }

    public void setPv(Double pv) {
        this.pv = pv;
    }

    public String getSeries1() {
        return series1;
    }

    public void setSeries1(String series1) {
        this.series1 = series1;
    }

    public String getSeries2() {
        return series2;
    }

    public void setSeries2(String series2) {
        this.series2 = series2;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
