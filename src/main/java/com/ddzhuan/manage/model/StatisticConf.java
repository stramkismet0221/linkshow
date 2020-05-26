package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiang yong tao
 * @date 2019/8/22 11:33
 */
public class StatisticConf implements Serializable {

    private static final long serialVersionUID = 6712802400698540565L;

    private Long id;

    private String name;

    private String url;

    /** 1 已删除,0正常 */
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
