package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;

/**
 * 产品信息
 *
 * @author likeke
 * @date 2019/09/02
 */
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 2802770905812623389L;

    /**
     * 产品id
     */
    private String id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品采购值
     */
    private Integer number;
    /**
     *
     */
    private String s;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
