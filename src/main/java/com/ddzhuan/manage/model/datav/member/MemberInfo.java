package com.ddzhuan.manage.model.datav.member;

import java.io.Serializable;

/**
 * 会员信息
 *
 * @author likeke
 * @date 2019/09/02
 */
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = -4833667348137281123L;

    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private Double value;
    /**
     * s
     */
    private String s;
    /**
     * attribute
     */
    private String attribute;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
