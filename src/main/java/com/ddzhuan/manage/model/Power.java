package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:30
 */
public class Power implements Serializable {

    private static final long serialVersionUID = -2651712866303343392L;

    /** 权限id */
    private Long id;

    /** 系统id */
    private Long systemId;

    /** 权限名称 */
    private String name;

    /** 权限编码 */
    private String code;

    /** 权限描述 */
    private String description;

    /** 权限类型 2、系统权限，1、菜单权限 */
    private Long type;

    private Date createTime;

    private Date modifyTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
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

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
