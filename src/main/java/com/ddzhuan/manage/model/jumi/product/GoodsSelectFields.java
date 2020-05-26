package com.ddzhuan.manage.model.jumi.product;

/**
 * 商品查询属性封装成对象
 *
 * @author likeke
 * @date 2020/01/09
 */
public class GoodsSelectFields {

    /**
     * 销售状态、展示类型  1：销售中 2、已下架 3、实体 4、电商
     */
    private Integer status;
    /**
     * 搜索关键字，模糊匹配商品名称、条形码、编码、简码
     */
    private String keyword;
    /**
     * 商品分类id
     */
    private Long goodsTypeId;
    /**
     * 搜索的价格区间类型：1、零售价 2、设备价 3、网店价 4、成本价, 5,批发价
     */
    private Integer priceType;
    /**
     * 搜索价格区间开始值
     */
    private Double priceRangeStart;
    /**
     * 搜索价格区间结束值
     */
    private Double priceRangeEnd;
    /**
     * 总销量区间开始值
     */
    private Double salesRangeStart;
    /**
     * 总销量区间结束值
     */
    private Double salesRangeEnd;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Double getPriceRangeStart() {
        return priceRangeStart;
    }

    public void setPriceRangeStart(Double priceRangeStart) {
        this.priceRangeStart = priceRangeStart;
    }

    public Double getPriceRangeEnd() {
        return priceRangeEnd;
    }

    public void setPriceRangeEnd(Double priceRangeEnd) {
        this.priceRangeEnd = priceRangeEnd;
    }

    public Double getSalesRangeStart() {
        return salesRangeStart;
    }

    public void setSalesRangeStart(Double salesRangeStart) {
        this.salesRangeStart = salesRangeStart;
    }

    public Double getSalesRangeEnd() {
        return salesRangeEnd;
    }

    public void setSalesRangeEnd(Double salesRangeEnd) {
        this.salesRangeEnd = salesRangeEnd;
    }

}
