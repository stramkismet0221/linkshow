package com.ddzhuan.manage.model;

import java.io.Serializable;

/**
 * 友朋设备运营配置（场地、价格、货架等）
 *
 * @author likeke
 * @date 2019/06/20
 */
public class YpTerminalConfig implements Serializable{

    private static final long serialVersionUID = -19805654980967215L;
    /**
     * 设备id
     */
    private String terminalId;
    /**
     * 场地id
     */
    private String placeId;
    /**
     * 价格策略id
     */
    private String priceRuleId;
    /**
     * 虚拟货架id
     */
    private String virtualShelfId;
    /**
     * 地址
     */
    private String address;
    /**
     * 场地
     */
    private YpTerminalPlace ypTerminalPlace;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPriceRuleId() {
        return priceRuleId;
    }

    public void setPriceRuleId(String priceRuleId) {
        this.priceRuleId = priceRuleId;
    }

    public String getVirtualShelfId() {
        return virtualShelfId;
    }

    public void setVirtualShelfId(String virtualShelfId) {
        this.virtualShelfId = virtualShelfId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public YpTerminalPlace getYpTerminalPlace() {
        return ypTerminalPlace;
    }

    public void setYpTerminalPlace(YpTerminalPlace ypTerminalPlace) {
        this.ypTerminalPlace = ypTerminalPlace;
    }

}
