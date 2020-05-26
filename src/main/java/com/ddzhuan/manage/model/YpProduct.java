package com.ddzhuan.manage.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * 友朋商品
 *
 * @author likeke
 * @date 2019/07/15
 */
public class YpProduct implements Serializable {

    private static final long serialVersionUID = -5616681614594763877L;

    /**
     * 商品条形码
     */
    private String barCode;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品分类id
     */
    private String categoryId;
    /**
     * 商品分类名称（数据库中没有此字段）
     */
    private String categoryName;
    /**
     * 商品厂商
     */
    private String manufacturer;
    /**
     * 商品图片
     */
    private String imageUrl;
    /**
     * 商品固定宽图片
     */
    private String imageFixWidthUrl;
    /**
     * 商品占位 1/2
     */
    private Integer colSpan;
    /**
     * 第三方支付商品扩展参数
     */
    private String payExtend;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    private ExCodeProduct exCodeProduct;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageFixWidthUrl() {
        return imageFixWidthUrl;
    }

    public void setImageFixWidthUrl(String imageFixWidthUrl) {
        this.imageFixWidthUrl = imageFixWidthUrl;
    }

    public Integer getColSpan() {
        return colSpan;
    }

    public void setColSpan(Integer colSpan) {
        this.colSpan = colSpan;
    }

    public String getPayExtend() {
        return payExtend;
    }

    public void setPayExtend(String payExtend) {
        this.payExtend = payExtend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public ExCodeProduct getExCodeProduct() {
        return exCodeProduct;
    }

    public void setExCodeProduct(ExCodeProduct exCodeProduct) {
        this.exCodeProduct = exCodeProduct;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
