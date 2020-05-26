package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单
 *
 * @author likeke
 * @date 2019/07/01
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = -3094239249278373633L;
    /**
     * 菜单id
     */
    private Long id;
    /**
     * 父级菜单id
     */
    private Long pid;
    /**
     * 系统id
     */
    private Long systemId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 菜单描述
     */
    private String description;
    /**
     * 访问地址
     */
    private String visitUrl;
    /**
     * 同级菜单排序
     */
    private Integer sno;
    /**
     * 菜单图像
     */
    private String img;
    /**
     * 状态 0：删除 1：正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    /**以下为数据库中不存在字段**/
    /**
     * 是否被选中
     */
    private boolean isSelected;
    /**
     * 子菜单
     */
    private List<Menu> childMenus;
    /**
     * 所属系统名称
     */
    private String systemName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", systemId='" + systemId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", visitUrl=" + visitUrl +
                ", sno=" + sno +
                ", img=" + img +
                ", status=" + status +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
