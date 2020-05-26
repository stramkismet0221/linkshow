package com.ddzhuan.manage.model.dashboard;

import java.util.Date;

/**
 * 售货机经营情况、经营分析
 * <pre>
 *     数据展示VO：根据订单表查询记录组装为数据展示层领域模型VO
 * </pre>
 *
 * @author likeke
 * @date 2019/12/04
 */
public class BusAnalysis {

    /**
     * 交易金额
     */
    private Double transAmount;
    /**
     * 交易笔数
     */
    private Integer transNum;
    /**
     * 退款金额
     */
    private Double refundAmount;
    /**
     * 退款笔数
     */
    private Integer refundNum;
    /**
     * 成交额
     */
    private Double turnoverAmount;
    /**
     * 同比增长/下降百分比
     */
    private Double compared;
    /**
     * 毛利
     */
    private Double grossProfit;
    /**
     * 日期字符串
     */
    private String dayStr;
    /**
     * 统计开始时间
     */
    private Date startTime;
    /**
     * 统计结束时间
     */
    private Date endTime;

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public Integer getTransNum() {
        return transNum;
    }

    public void setTransNum(Integer transNum) {
        this.transNum = transNum;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Double getTurnoverAmount() {
        return turnoverAmount;
    }

    public void setTurnoverAmount(Double turnoverAmount) {
        this.turnoverAmount = turnoverAmount;
    }

    public Double getCompared() {
        return compared;
    }

    public void setCompared(Double compared) {
        this.compared = compared;
    }

    public Double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
