package com.ddzhuan.manage.model;

import java.io.Serializable;

/**
 * 订单用户基本信息
 *
 * @author likeke
 * @date 2019/11/27
 */
public class OrderUser implements Serializable {

    private static final long serialVersionUID = 2001355857926484680L;

    /**
     * id
     */
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 购买用户openid
     */
    private String openId;
    /**
     * 购买用户昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 所在省份
     */
    private String province;
    /**
     * 所在市
     */
    private String city;
    /**
     * imageUrl
     */
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
