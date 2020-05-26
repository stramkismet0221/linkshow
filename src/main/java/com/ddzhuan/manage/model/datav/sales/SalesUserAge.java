package com.ddzhuan.manage.model.datav.sales;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户年龄组
 * @author likeke
 * @date 2019/10/30
 */
public class SalesUserAge implements Serializable {

    private static final long serialVersionUID = -4893170609606497304L;
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
     * 年龄段
     */
    private String ageGroup;
    /**
     * 人数
     */
    private Integer userNumber;
    /**
     * 新增用户数
     */
    private Integer newUsers;
    /**
     * 同比增长率
     */
    private Integer tGrowth;
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

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public Integer getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(Integer newUsers) {
        this.newUsers = newUsers;
    }

    public Integer getTGrowth() {
        return tGrowth;
    }

    public void setTGrowth(Integer tGrowth) {
        this.tGrowth = tGrowth;
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
