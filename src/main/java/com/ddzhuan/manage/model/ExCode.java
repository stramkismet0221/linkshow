package com.ddzhuan.manage.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/31 18:07
 */
public class ExCode implements Serializable {


    private static final long serialVersionUID = 2967717162823325633L;

    private Long id;

    /** 活动ID */
    private String actId;

    /** 活动名称 */
    private String actName;

    /** 兑换码总数 */
    private Integer exCodeCount;

    /** 锁定状态 */
    private Integer locked;

    /** 设备类型 1:全部,2部分 */
    private String machineType;

    /** 兑换码适用的设备 */
    private String machines;

    /** 失效时间类型  */
    private Integer expireType;

    /** 截止时间 */
    private Date expireTime;

    private String expireTimeStr;

    /** 有效时间天数 */
    private Integer expireDays;

    /** 状态 */
    private Integer status;

    private Date createTime;

    private Date modifyTime;

    /** 已领取数量 */
    private Integer receivedCount;

    /** 已兑换数量 */
    private Integer exchangedCount;

    /** 兑换码适用的商品 */
    private String exCodeProducts;

    private List<ExCodeProduct> products;

    private List<YpTerminal> terminals;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Integer getExCodeCount() {
        return exCodeCount;
    }

    public void setExCodeCount(Integer exCodeCount) {
        this.exCodeCount = exCodeCount;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getMachines() {
        return machines;
    }

    public void setMachines(String machines) {
        this.machines = machines;
    }

    public Integer getExpireType() {
        return expireType;
    }

    public void setExpireType(Integer expireType) {
        this.expireType = expireType;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(Integer expireDays) {
        this.expireDays = expireDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getExCodeProducts() {
        return exCodeProducts;
    }

    public void setExCodeProducts(String exCodeProducts) {
        this.exCodeProducts = exCodeProducts;
    }

    public Integer getReceivedCount() {
        return receivedCount;
    }

    public void setReceivedCount(Integer receivedCount) {
        this.receivedCount = receivedCount;
    }

    public Integer getExchangedCount() {
        return exchangedCount;
    }

    public void setExchangedCount(Integer exchangedCount) {
        this.exchangedCount = exchangedCount;
    }

    public List<ExCodeProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ExCodeProduct> products) {
        this.products = products;
    }

    public List<YpTerminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<YpTerminal> terminals) {
        this.terminals = terminals;
    }

    public String getExpireTimeStr() {
        return expireTimeStr;
    }

    public void setExpireTimeStr(String expireTimeStr) {
        this.expireTimeStr = expireTimeStr;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
