package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;

/**
 * 产品种类信息
 *
 * @author likeke
 * @date 2019/09/02
 */
public class CategoryInfo implements Serializable {

    private static final long serialVersionUID = -210400710918880352L;

    /**
     * 品类id
     */
    private String id;
    /**
     * 品类名称
     */
    private String name;
    /**
     * 品类采购值
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
