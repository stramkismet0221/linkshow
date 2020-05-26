package com.ddzhuan.manage.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 数据字典
 *
 * @author likeke
 * @date 2019/08/14
 */
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 2846961339486570185L;
    /**
     * id
     */
    private Long id;
    /**
     * code
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private String value;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 字典类型
     */
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
