package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;

/**
 * 会员统计--按月份统计
 *
 * @author likeke
 * @date 2019/09/09
 */
public class MonthData implements Serializable {

    private static final long serialVersionUID = 2578478774201183125L;

    /**
     * 月份
     */
    private String month;
    /**
     * 销售额
     */
    private String sales;
    /**
     * value
     */
    private String value;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
