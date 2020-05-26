package com.ddzhuan.manage.model;

import java.io.Serializable;

/**
 * 友朋设备货道
 *
 * @author likeke
 * @date 2019/06/20
 */
public class YpTerminalCargo implements Serializable{

    private static final long serialVersionUID = -268492588462022450L;
    /**
     * 货道id
     */
    private String id;
    /**
     * 设备id
     */
    private String terminalId;
    /**
     * 容量
     */
    private Integer capacity;
    /**
     * 货道编号
     */
    private String displayName;
    /**
     * 货柜（A、B、C、D）
     */
    private String cabinetName;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 是否正常售卖状态 1：是 0：否
     */
    private Integer sellStatus;
    /**
     * 货柜商品条形码
     */
    private String barCode;
    /**
     * 是否制冷 1：是 0：否
     */
    private Integer coldStatus;
    /**
     * 是否加热 1：是 0：否
     */
    private Integer hotStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getColdStatus() {
        return coldStatus;
    }

    public void setColdStatus(Integer coldStatus) {
        this.coldStatus = coldStatus;
    }

    public Integer getHotStatus() {
        return hotStatus;
    }

    public void setHotStatus(Integer hotStatus) {
        this.hotStatus = hotStatus;
    }
}
