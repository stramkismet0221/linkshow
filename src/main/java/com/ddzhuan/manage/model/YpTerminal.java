package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 友朋设备
 *
 * @author likeke
 * @date 2019/06/20
 */
public class YpTerminal implements Serializable{

    private static final long serialVersionUID = -4314121923874441061L;

    /**
     * 设备id
     */
    private String id;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备号
     */
    private String deviceCode;
    /**
     * 货柜数
     */
    private Integer cabinetCount;
    /**
     * 地点id
     */
    private String placeId;
    /**
     * 运营配置
     */
    private YpTerminalConfig ypTerminalConfig;
    /**
     * 货道
     */
    private List<YpTerminalCargo> ypTerminalCargoList;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Integer getCabinetCount() {
        return cabinetCount;
    }

    public void setCabinetCount(Integer cabinetCount) {
        this.cabinetCount = cabinetCount;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public YpTerminalConfig getYpTerminalConfig() {
        return ypTerminalConfig;
    }

    public void setYpTerminalConfig(YpTerminalConfig ypTerminalConfig) {
        this.ypTerminalConfig = ypTerminalConfig;
    }

    public List<YpTerminalCargo> getYpTerminalCargoList() {
        return ypTerminalCargoList;
    }

    public void setYpTerminalCargoList(List<YpTerminalCargo> ypTerminalCargoList) {
        this.ypTerminalCargoList = ypTerminalCargoList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
