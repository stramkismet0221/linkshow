package com.ddzhuan.manage.model;

import java.io.Serializable;

/**
 * @author jiang yong tao
 * @date 2019/7/31 18:10
 */
public class ExCodeProduct implements Serializable {

    private static final long serialVersionUID = 6914964693288280122L;

    private Long id;

    /** 关联兑换码库 id */
    private Long exCodeId;

    /** 商品名称 */
    private String productName;

    /** 商品条形码 */
    private String barcode;

    /** 商品兑换码数量 */
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExCodeId() {
        return exCodeId;
    }

    public void setExCodeId(Long exCodeId) {
        this.exCodeId = exCodeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
