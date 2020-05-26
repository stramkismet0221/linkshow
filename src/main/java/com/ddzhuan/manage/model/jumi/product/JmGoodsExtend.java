package com.ddzhuan.manage.model.jumi.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jiang yong tao
 */
public class JmGoodsExtend implements Serializable {

    private static final long serialVersionUID = -7549224172474719772L;

    /**
     *
     * @mbg.generated Mon Dec 30 10:24:02 CST 2019
     */
    private Long id;

    /**
     * 自定义字段名称
     *
     * @mbg.generated Mon Dec 30 10:24:02 CST 2019
     */
    private String name;

    /**
     * 是否必填1是0否
     *
     * @mbg.generated Mon Dec 30 10:24:02 CST 2019
     */
    private Integer isNotNull;

    /**
     * 商品类型1实物2虚拟
     * @mbg.generated Mon Dec 30 10:24:02 CST 2019
     */
    private Integer goodsType;

    /**
     * 字段类型
     * 1,"单行文本框"、2,"下拉选项"、3,"单选"、4,"多选"、5,"日历"
     */
    private Integer fieldType;

    private String fieldValue;

    private Date createTime;

    private Date updateTime;

    private List<String> items;

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

    public Integer getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(Integer isNotNull) {
        this.isNotNull = isNotNull;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
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

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}