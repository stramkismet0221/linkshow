package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;

/**
 * 省市相关信息
 *
 * @author likeke
 * @date 2019/09/02
 */
public class AreaInfo implements Serializable {

    private static final long serialVersionUID = -1710825451647877282L;
    /**
     * 省市id
     */
    private String id;
    /**
     * 省市名称
     */
    private String name;
    /**
     * 省市采购值
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
