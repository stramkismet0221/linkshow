/**
 * 
 */
package com.ddzhuan.manage.model.jumi.product;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jrj
 *
 */
public class GoodsBrand implements Serializable {

	private static final  long serialVersionUID = 7288074776915360954L;
	private Long id;
	private String name; //品牌名称
	private Long sno; //序号
	private String logo; //品牌logo图标url
	private String linkUrl; //品牌url
	private String description; //简介
	private Date createTime; //创建时间
	private Date updateTime; //修改时间
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
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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
	
	
	
	
	
}
