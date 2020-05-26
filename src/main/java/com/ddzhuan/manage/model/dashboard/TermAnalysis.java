package com.ddzhuan.manage.model.dashboard;

/**
 * 设备经营情况、销量、利润经营分析
 * <pre>
 *     数据展示VO：根据订单表查询记录组装为数据展示层领域模型VO
 * </pre>
 *
 * @author likeke
 * @date 2019/12/05
 */
public class TermAnalysis {

    /**
     * 设备id
     */
    private String terminalId;
    /**
     * 设备名称
     */
    private String terminalName;
    /**
     * 今日交易额
     */
    private Double todTransAmount;
    /**
     * 昨日交易额
     */
    private Double yesTransAmount;
    /**
     * 本月交易额
     */
    private Double monthTransAmount;
    /**
     * 今日毛利
     */
    private Double todGrossProfit;
    /**
     * 昨日毛利
     */
    private Double yesGrossProfit;
    /**
     * 本月毛利
     */
    private Double monthGrossProfit;
    /**
     * 今日订单量
     */
    private Integer todSalesVolume;
    /**
     * 昨日订单量
     */
    private Integer yesSalesVolume;
    /**
     * 本月订单量
     */
    private Integer monthSalesVolume;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public Double getTodTransAmount() {
        return todTransAmount;
    }

    public void setTodTransAmount(Double todTransAmount) {
        this.todTransAmount = todTransAmount;
    }

    public Double getYesTransAmount() {
        return yesTransAmount;
    }

    public void setYesTransAmount(Double yesTransAmount) {
        this.yesTransAmount = yesTransAmount;
    }

    public Double getMonthTransAmount() {
        return monthTransAmount;
    }

    public void setMonthTransAmount(Double monthTransAmount) {
        this.monthTransAmount = monthTransAmount;
    }

    public Double getTodGrossProfit() {
        return todGrossProfit;
    }

    public void setTodGrossProfit(Double todGrossProfit) {
        this.todGrossProfit = todGrossProfit;
    }

    public Double getYesGrossProfit() {
        return yesGrossProfit;
    }

    public void setYesGrossProfit(Double yesGrossProfit) {
        this.yesGrossProfit = yesGrossProfit;
    }

    public Double getMonthGrossProfit() {
        return monthGrossProfit;
    }

    public void setMonthGrossProfit(Double monthGrossProfit) {
        this.monthGrossProfit = monthGrossProfit;
    }

    public Integer getTodSalesVolume() {
        return todSalesVolume;
    }

    public void setTodSalesVolume(Integer todSalesVolume) {
        this.todSalesVolume = todSalesVolume;
    }

    public Integer getYesSalesVolume() {
        return yesSalesVolume;
    }

    public void setYesSalesVolume(Integer yesSalesVolume) {
        this.yesSalesVolume = yesSalesVolume;
    }

    public Integer getMonthSalesVolume() {
        return monthSalesVolume;
    }

    public void setMonthSalesVolume(Integer monthSalesVolume) {
        this.monthSalesVolume = monthSalesVolume;
    }
}
