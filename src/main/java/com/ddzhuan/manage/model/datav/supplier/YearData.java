package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;

/**
 * 年度销售额增速数据分析
 *
 * @author likeke
 * @date 2019/09/02
 */
public class YearData implements Serializable {

    private static final long serialVersionUID = -7485914892575981935L;

    /**
     * 年份
     */
    private String year;
    /**
     * 销售额增速
     */
    private Double growthValue;
    /**
     *
     */
    private String s;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(Double growthValue) {
        this.growthValue = growthValue;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
