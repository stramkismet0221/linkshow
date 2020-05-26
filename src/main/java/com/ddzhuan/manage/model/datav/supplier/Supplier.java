package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;

/**
 * 供应商信息
 *
 * @author likeke
 * @date 2019/09/02
 */
public class Supplier implements Serializable {

    private static final long serialVersionUID = -1195548937650061533L;
    /**
     * 供应商id
     */
    private String id;
    /**
     * 供应商名称
     */
    private String name;
    /**
     *
     */
    private String value;
    /**
     * 采购额
     */
    private Double amount;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
