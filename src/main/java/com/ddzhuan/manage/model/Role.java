package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:32
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -1167831605120285843L;

    private Long id;

    private Long systemId;

    /** 角色名称 */
    private String name;

    /** 角色唯一编码 */
    private String code;

    /** 角色描述 */
    private String description;

    private Date createTime;

    private Date modifyTime;

    private List<User> users;

    private List<Power> powers;

    private List<Menu> menus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return  "Role{" + "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", users='" + users + '\'' +
                ", powers='" + powers + '\'' +
                ", menus='" + menus + '\'' +
                '}';
    }
}
