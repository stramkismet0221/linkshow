package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 电子烟售货机安装记录
 *
 * @author likeke
 * @date 2019/10/08
 */
public class SellSmokeInstall implements Serializable {

    private static final long serialVersionUID = -3556811406432832277L;
    /**
     * id
     */
    private Long id;
    /**
     * 渠道商id
     */
    private Long agentId;
    /**
     * 渠道商名称
     */
    private String agentName;
    /**
     * 安装商户
     */
    private String store;
    /**
     * 商户联系人
     */
    private String storeUser;
    /**
     * 商户地址
     */
    private String address;
    /**
     * 商户联系人手机号码
     */
    private Long mobile;
    /**
     * 预约安装时间
     */
    private Date appointTime;
    private String appointTimeStr;
    /**
     * 设备类型
     */
    private String terminalType;
    /**
     * 安装设备数量
     */
    private Integer tNumber;
    /**
     * 申请人id
     */
    private Long applyUserId;
    /**
     * 申请人姓名
     */
    private String applyUserName;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 申请人手机号码
     */
    private Long applyUserMobile;
    /**
     * 申请备注
     */
    private String applyRemark;
    /**
     * 审核用户id
     */
    private Long auditUserId;
    /**
     * 审核用户名
     */
    private String auditUserName;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核人手机号码
     */
    private Long auditUserMobile;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 安装用户id
     */
    private Long installUserId;
    /**
     * 安装用户名
     */
    private String installUserName;
    /**
     * 安装时间
     */
    private Date installTime;
    /**
     * 安装人手机号码
     */
    private Long installUserMobile;
    /**
     * 安装备注
     */
    private String installRemark;
    /**
     * 状态：0、待审核 1、处理中 2、已安装 3、取消安装
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    private List<SellSmokeTerminal> sellSmokeTerminals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStoreUser() {
        return storeUser;
    }

    public void setStoreUser(String storeUser) {
        this.storeUser = storeUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public String getAppointTimeStr() {
        return appointTimeStr;
    }

    public void setAppointTimeStr(String appointTimeStr) {
        this.appointTimeStr = appointTimeStr;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public Integer gettNumber() {
        return tNumber;
    }

    public void settNumber(Integer tNumber) {
        this.tNumber = tNumber;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getApplyUserMobile() {
        return applyUserMobile;
    }

    public void setApplyUserMobile(Long applyUserMobile) {
        this.applyUserMobile = applyUserMobile;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditUserMobile() {
        return auditUserMobile;
    }

    public void setAuditUserMobile(Long auditUserMobile) {
        this.auditUserMobile = auditUserMobile;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Long getInstallUserId() {
        return installUserId;
    }

    public void setInstallUserId(Long installUserId) {
        this.installUserId = installUserId;
    }

    public String getInstallUserName() {
        return installUserName;
    }

    public void setInstallUserName(String installUserName) {
        this.installUserName = installUserName;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Long getInstallUserMobile() {
        return installUserMobile;
    }

    public void setInstallUserMobile(Long installUserMobile) {
        this.installUserMobile = installUserMobile;
    }

    public String getInstallRemark() {
        return installRemark;
    }

    public void setInstallRemark(String installRemark) {
        this.installRemark = installRemark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<SellSmokeTerminal> getSellSmokeTerminals() {
        return sellSmokeTerminals;
    }

    public void setSellSmokeTerminals(List<SellSmokeTerminal> sellSmokeTerminals) {
        this.sellSmokeTerminals = sellSmokeTerminals;
    }
}
