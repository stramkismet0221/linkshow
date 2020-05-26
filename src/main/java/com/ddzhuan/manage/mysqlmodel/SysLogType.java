package com.ddzhuan.manage.mysqlmodel;

import java.io.Serializable;

/**
 * 日志类型
 * @author wyp
 *
 */
public class SysLogType implements Serializable{

	private Integer logTypeId;
	
	private String LogTypeName;
	
	private Integer active; // 0：关闭日志； 1：打开日志

	private String alertLevel;
	
	private String alertMobile;
	
	public Integer getLogTypeId() {
		return logTypeId;
	}

	public void setLoginTypeId(Integer logTypeId) {
		this.logTypeId = logTypeId;
	}

	public String getLogTypeName() {
		return LogTypeName;
	}

	public void setLogTypeName(String logTypeName) {
		LogTypeName = logTypeName;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}

	public String getAlertMobile() {
		return alertMobile;
	}

	public void setAlertMobile(String alertMobile) {
		this.alertMobile = alertMobile;
	}

	public void setLogTypeId(Integer logTypeId) {
		this.logTypeId = logTypeId;
	}
	
}
