package com.ddzhuan.manage.model.jumi.order;

import java.io.Serializable;

/**
 * @author jiang yong tao
 * @date 2020/1/17 14:16
 */
public class OrderPost implements Serializable {

    private static final long serialVersionUID = -4224617771250562947L;

    private Long id;

    /**
     * 配送方式
     */
    private String postType;

    /**
     * 开通状态
     */
    private Integer openStatus;

    /**
     * 默认状态
     */
    private Integer deafStatus;

    /**
     * 配送价格
     */
    private Double postPrice;

    /**
     * 配送范围
     */
    private Double postAround;

    /**
     * 序号
     */
    private Integer sno;

    /**
     * 是否包邮
     */
    private Integer freeShippingStatus;

    /**
     * 订单满足多少金额包邮
     */
    private Double enoughPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Integer getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Integer openStatus) {
        this.openStatus = openStatus;
    }

    public Integer getDeafStatus() {
        return deafStatus;
    }

    public void setDeafStatus(Integer deafStatus) {
        this.deafStatus = deafStatus;
    }

    public Double getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(Double postPrice) {
        this.postPrice = postPrice;
    }

    public Double getPostAround() {
        return postAround;
    }

    public void setPostAround(Double postAround) {
        this.postAround = postAround;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getFreeShippingStatus() {
        return freeShippingStatus;
    }

    public void setFreeShippingStatus(Integer freeShippingStatus) {
        this.freeShippingStatus = freeShippingStatus;
    }

    public Double getEnoughPrice() {
        return enoughPrice;
    }

    public void setEnoughPrice(Double enoughPrice) {
        this.enoughPrice = enoughPrice;
    }
}
