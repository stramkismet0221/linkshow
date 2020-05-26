package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员相关统计
 *
 * @author likeke
 * @date 2019/10/28
 */
public class MemberTotal implements Serializable {

    private static final long serialVersionUID = 1683672264095692487L;
    /**
     * id
     */
    private Long id;
    /**
     * 运营商id
     */
    private String oId;
    /**
     * 运营商名称
     */
    private String oName;
    /**
     * 日期
     */
    private String timeStr;
    /**
     * 会员数（单位：万人）
     */
    private Double memberNum;
    /**
     * 类型 1、今年按月份统计（实线）2、去年按月份统计（虚线）3、总会员数
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOId() {
        return oId;
    }

    public void setOId(String oId) {
        this.oId = oId;
    }

    public String getOName() {
        return oName;
    }

    public void setOName(String oName) {
        this.oName = oName;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Double getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Double memberNum) {
        this.memberNum = memberNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
