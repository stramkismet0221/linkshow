package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author likeke
 * @date 2019/07/01
 */
public class User implements Serializable {

	private static final long serialVersionUID = -4031627475340869999L;

	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 电话号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 头像地址
	 */
	private String headPicUrl;
	/**
	 * 锁定状态 0：未锁定 1：锁定
	 */
	private Integer locked;
	/**
	 * 用户状态 0：已删除 1：正常
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
	/**
	 * 电子烟代理商id
	 */
	private Long agentId;
	/**
	 * 电子烟代理商名称
	 */
	private String agentName;

	private Map<String,List<Menu>> menus;

	private List<Role> roles;

	private List<SystemInfo> systemInfos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadPicUrl() {
		return headPicUrl;
	}

	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
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

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Map<String, List<Menu>> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, List<Menu>> menus) {
		this.menus = menus;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<SystemInfo> getSystemInfos() {
		return systemInfos;
	}

	public void setSystemInfos(List<SystemInfo> systemInfos) {
		this.systemInfos = systemInfos;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id +
				", userName='" + userName + '\'' +
				", realName='" + realName + '\'' +
				", password='" + password + '\'' +
				", sex=" + sex +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", headPicUrl='" + headPicUrl + '\'' +
				", locked=" + locked +
				", status=" + status +
				", menus=" + menus +
				", roles=" + roles +
				", systemInfos=" + systemInfos +
				", createTime=" + createTime +
				", modifyTime=" + modifyTime +
				'}';
	}
}
