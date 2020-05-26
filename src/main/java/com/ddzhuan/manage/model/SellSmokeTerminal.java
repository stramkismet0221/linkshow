package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 电子烟售货机设备信息
 *
 * @author likeke
 * @date 2019/10/11
 */
public class SellSmokeTerminal implements Serializable {

    private static final long serialVersionUID = 1973289062606601900L;

    /**
     * id
     */
    private Long id;
    /**
     * 安装信息id
     */
    private Long installId;
    /**
     * 设备类型
     */
    private String terminalType;
    /**
     * 设备类型名称
     */
    private String terminalTypeName;
    /**
     * 设备编号
     */
    private String terminalCode;
    /**
     * 设备名称
     */
    private String terminalName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstallId() {
        return installId;
    }

    public void setInstallId(Long installId) {
        this.installId = installId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTerminalTypeName() {
        return terminalTypeName;
    }

    public void setTerminalTypeName(String terminalTypeName) {
        this.terminalTypeName = terminalTypeName;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
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
}
