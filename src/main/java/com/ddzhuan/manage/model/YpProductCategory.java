package com.ddzhuan.manage.model;


import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 友朋商品分类树
 *
 * @author likeke
 * @date 2019/07/15
 */
public class YpProductCategory implements Serializable {

    private static final long serialVersionUID = -1449840994154976304L;
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级id
     */
    private String pid;
    /**
     * 级别
     */
    private Integer levels;
    /**
     * 子集
     */
    private List<YpProductCategory> child = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public List<YpProductCategory> getChild() {
        return child;
    }

    public void setChild(List<YpProductCategory> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
