package com.ddzhuan.manage.model.dashboard;

/**
 * 产品经营情况、销量、利润经营分析
 * <pre>
 *     数据展示VO：根据订单表查询记录组装为数据展示层领域模型VO
 * </pre>
 *
 * @author likeke
 * @date 2019/12/05
 */
public class ProdAnalysis {

    /**
     * 商品条形码
     */
    private String barCode;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 交易金额
     */
    private Double transAmount;
    /**
     * 成交额
     */
    private Double turnoverAmount;
    /**
     * 销量
     */
    private Integer salesVolume;
    /**
     * 毛利
     */
    private Double grossProfit;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public Double getTurnoverAmount() {
        return turnoverAmount;
    }

    public void setTurnoverAmount(Double turnoverAmount) {
        this.turnoverAmount = turnoverAmount;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }

}
