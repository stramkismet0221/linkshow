package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author jiang yong tao
 * @date 2019/7/22 16:13
 */
public class ThirdPay implements Serializable {


    private static final long serialVersionUID = 2822484998413871695L;

    private Long id;

    private String name;

    private String icon;

    private Integer status;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", ThirdPay.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("icon='" + icon + "'")
                .add("status=" + status)
                .add("createTime=" + createTime)
                .add("modifyTime=" + modifyTime)
                .toString();
    }
}
