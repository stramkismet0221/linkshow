package com.ddzhuan.manage.model.jumi.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/26 11:20
 */
public class JmGoodsType implements Serializable {

    private static final long serialVersionUID = 857511976084773748L;

    private Long id;

    /**
     * 商品分类名称
     */
    private String name;

    /**
     * 序号
     */
    private Integer sno;

    /**
     * 商品分类编码
     */
    private String code;

    /**
     * 展示类型（1、电商展示 2、实体展示 ）
     */
    private String showType;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 级别：一级分类、二级分类、、、n级分类
     */
    private Integer level;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 父分类
     */
    private JmGoodsType parent;

    /**
     * 子分类集合
     */
    private List<JmGoodsType> children;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 选中（扩展属性）
     */
    private Boolean checked;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public JmGoodsType getParent() {
        return parent;
    }

    public void setParent(JmGoodsType parent) {
        this.parent = parent;
    }

    public List<JmGoodsType> getChildren() {
        return children;
    }

    public void setChildren(List<JmGoodsType> children) {
        this.children = children;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
