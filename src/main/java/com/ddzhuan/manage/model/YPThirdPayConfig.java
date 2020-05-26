package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author jiang yong tao
 * @date 2019/7/22 17:52
 */
public class YPThirdPayConfig implements Serializable {


    private static final long serialVersionUID = 1644418469850013264L;

    private Long id;

    /** 支付跳转地址 */
    private String payUrl;

    /** 第三方服务API地址 */
    private String serverUrl;

    /** 签名密钥 */
    private String signKey;

    /** 支付渠道 */
    private String payType;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", YPThirdPayConfig.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("payUrl='" + payUrl + "'")
                .add("serverUrl='" + serverUrl + "'")
                .add("signKey='" + signKey + "'")
                .add("payType='" + payType + "'")
                .add("status=" + status)
                .add("createTime=" + createTime)
                .add("modifyTime=" + modifyTime)
                .toString();
    }
}
