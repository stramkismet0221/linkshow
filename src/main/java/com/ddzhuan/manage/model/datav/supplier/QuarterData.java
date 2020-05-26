package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;

/**
 * 采购季度数据分析
 *
 * @author likeke
 * @date 2019/09/02
 */
public class QuarterData implements Serializable {

    private static final long serialVersionUID = 3914112029283698003L;

    /**
     * 标题（一季度采购进度...）
     */
    private String title;
    /**
     * 图形模块编号
     */
    private String no;
    /**
     * 占比
     */
    private Double percent;
    /**
     * 总额（单位：万）
     */
    private Integer amount;
    /**
     * 同比增长（单位：%）
     */
    private Integer growth;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }
}
