package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单记录
 *
 * @author likeke
 * @date 2019/08/29
 */
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = -4300832003467189126L;

    /**
     * 订单id
     */
    private Long id;
    /**
     * 订单用户id
     */
    private String userId;
    /**
     *
     */
    private String openId;
    /**
     * 订单用户昵称
     */
    private String nickName;
    /**
     * 运营商id
     */
    private String oId;
    /**
     * 商品条码
     */
    private String barCode;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 订单金额
     */
    private Double originalPrice;
    /**
     * 实付金额
     */
    private Double price;
    /**
     * 折扣
     */
    private Double discount;
    /**
     * 成本价
     */
    private Double costPrice;
    /**
     * 利润
     */
    private Double profit;
    /**
     * 温度【0、常温 1、冷 2、热】
     */
    private Integer temperature;
    /**
     * 支付方式【1、微信 2、支付宝 4、现金支付 5、微信支付（代收） 6、支付宝支付（代收） 99、第三方支付 100、运营商兑换码】
     */
    private Integer payType;
    /**
     * 订单号
     */
    private String receiptNo;
    /**
     * 支付时间（时间戳，精确到秒）
     */
    private Date payTime;
    /**
     * 交易哈希值
     */
    private String tradeNo;
    /**
     * 第三方支付类型（1、task支付 2、喵钻支付）
     */
    private Integer thirdPayType;
    /**
     * 退款状态
     */
    private Integer refundStatus;
    /**
     * 设备id
     */
    private String tId;
    /**
     * 设备名称
     */
    private String tName;
    /**
     * 设备地点id
     */
    private String placeId;
    /**
     * 设备所在地点名称
     */
    private String placeName;
    /**
     * 更新时间
     */
    private Date modifyTime;
    /**
     * task价格
     */
    private Double taskPrice;
    /**
     * 支付状态
     */
    private Integer payStatus;
    /**
     * 出货状态
     */
    private Integer outStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getThirdPayType() {
        return thirdPayType;
    }

    public void setThirdPayType(Integer thirdPayType) {
        this.thirdPayType = thirdPayType;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(Integer outStatus) {
        this.outStatus = outStatus;
    }
}
