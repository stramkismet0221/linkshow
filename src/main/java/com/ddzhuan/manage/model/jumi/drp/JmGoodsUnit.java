package com.ddzhuan.manage.model.jumi.drp;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiang yong tao
 * @date 2020/1/9 10:28
 */
public class JmGoodsUnit implements Serializable {

    private static final long serialVersionUID = 437540813565402500L;

    private Long id;

    /** 单位名称 */
    private String name;

    private Integer sno;

    private Date createTime;

    private Date updateTime;

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

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
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
