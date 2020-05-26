package com.ddzhuan.manage.model.dashboard;

/**
 * 设备平均交易额
 * <pre>
 *     数据展示VO：根据订单表查询记录组装为数据展示层领域模型VO
 * </pre>
 *
 * @author likeke
 * @date 2019/12/05
 */
public class TermAvgAnalysis {

    /**
     * 今日平均交易额
     */
    private Double todAvgTransAmount;
    /**
     * 昨日平均交易额
     */
    private Double yesAvgTransAmount;

    public Double getTodAvgTransAmount() {
        return todAvgTransAmount;
    }

    public void setTodAvgTransAmount(Double todAvgTransAmount) {
        this.todAvgTransAmount = todAvgTransAmount;
    }

    public Double getYesAvgTransAmount() {
        return yesAvgTransAmount;
    }

    public void setYesAvgTransAmount(Double yesAvgTransAmount) {
        this.yesAvgTransAmount = yesAvgTransAmount;
    }
}
