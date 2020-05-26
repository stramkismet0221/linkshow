package com.ddzhuan.manage.model.jumi.shop;

import java.util.Date;

public class ShopEmployee {
    private Long id;
    private String loginAccount;//登录账号
    private String password;//登录密码
    private String name;//员工姓名
    private Long phone;//联系手机
    private String headImg;//员工头像
    private Long employeeType;//员工类型
    private Long department;//员工部门，默认0 全部
    private Long loginTeerminal;//登录终端(1 云后台,2小程序端，3,设备终端)默认全部
    private Long sex;//性别(1男，2, 女  3 未知)默认1
    private Long roleId;//角色
    private String manageFacility;//是否管理所有设备（1 是 0 否，默认0）
    private Long shopId;//门店id
    private Long jobState;//状态(1在职,0离职)默认1
    private Long startState;//1启用 0 禁用  默认1

    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Long getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Long employeeType) {
        this.employeeType = employeeType;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public Long getLoginTeerminal() {
        return loginTeerminal;
    }

    public void setLoginTeerminal(Long loginTeerminal) {
        this.loginTeerminal = loginTeerminal;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getManageFacility() {
        return manageFacility;
    }

    public void setManageFacility(String manageFacility) {
        this.manageFacility = manageFacility;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getJobState() {
        return jobState;
    }

    public void setJobState(Long jobState) {
        this.jobState = jobState;
    }

    public Long getStartState() {
        return startState;
    }

    public void setStartState(Long startState) {
        this.startState = startState;
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
