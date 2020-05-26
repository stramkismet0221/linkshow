package com.ddzhuan.manage.model.jumi.order;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 购货订单实体
 * @author jiang yong tao
 * @date 2020/2/3 14:37
 */
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = -7267570325381839284L;

    private Long id;

    /** 业务类型
     *  1 购货,2 入库, 3 退货
     * */
    private Long businessType;

    /** 供应商ID */
    private Long supplierId;

    /** 供应商名字 */
    private String supplierName;

    /** 门店ID */
    private Long shopId;

    /** 门店名字 */
    private String shopName;

    /** 购货单号 */
    private String ghNo;

    /** 购货订单号 */
    private String ghdNo;

    /** 购货退货单号 */
    private String ghtNo;

    /** 交货日期 */
    private Date giveTime;

    /** 单据日期 */
    private Date poDate;

    /** 交货日期 */
    private String giveTimeStr;

    /** 单据日期 */
    private String poDateStr;

    /** 优惠率 */
    //             discountRate
    private Double discountRate;

    /** 优惠金额 */
    private Double discountPrice;

    /** 订单状态
     * 0,待入库,1已入库,2部分入库,3已关闭
     * */
    private Integer status;

    /** 备注 */
    private String remark;

    /** 审核状态
     * 1 审核, -1 反审核, 0待审核
     * */
    private Integer checkStatus;

    /**  制单人 */
    private String creator;

    /** 购货金额 */
    private Double totalPrice;

    /** 购货数量 */
    private Double totalCount;

    /** 优惠后金额 */
    private Double discountAfterPrice;

    /** 原购货数量 */
    private Double oldCount;

    /** 类型 1购货订单;2购货入库;3退货 */
    private Integer type;

    private Date createTime;

    private Date updateTime;

    private String startTime;

    private String endTime;

    private String keyWord;

    private List<Long> supplierIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Long businessType) {
        this.businessType = businessType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;
    }

    public List<Long> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<Long> supplierIds) {
        this.supplierIds = supplierIds;
    }

    public String getGhNo() {
        return ghNo;
    }

    public void setGhNo(String ghNo) {
        this.ghNo = ghNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGhdNo() {
        return ghdNo;
    }

    public void setGhdNo(String ghdNo) {
        this.ghdNo = ghdNo;
    }

    public String getGhtNo() {
        return ghtNo;
    }

    public void setGhtNo(String ghtNo) {
        this.ghtNo = ghtNo;
    }

    public Double getOldCount() {
        return oldCount;
    }

    public void setOldCount(Double oldCount) {
        this.oldCount = oldCount;
    }

    public Double getDiscountAfterPrice() {
        return discountAfterPrice;
    }

    public void setDiscountAfterPrice(Double discountAfterPrice) {
        this.discountAfterPrice = discountAfterPrice;
    }

    public String getGiveTimeStr() {
        return giveTimeStr;
    }

    public void setGiveTimeStr(String giveTimeStr) {
        this.giveTimeStr = giveTimeStr;
    }

    public String getPoDateStr() {
        return poDateStr;
    }

    public void setPoDateStr(String poDateStr) {
        this.poDateStr = poDateStr;
    }

    public Date getGiveTime() {
        return giveTime;
    }

    public void setGiveTime(Date giveTime) {
        this.giveTime = giveTime;
    }

    public Date getPoDate() {
        return poDate;
    }

    public void setPoDate(Date poDate) {
        this.poDate = poDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
