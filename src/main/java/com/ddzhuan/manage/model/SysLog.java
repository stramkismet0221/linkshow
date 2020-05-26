package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3631406859991945252L;
	/** 日志编号 */
	protected long sysLogId;
	/** 用户编号 */
	protected long userId;
	/** 用户名称 */
	protected String userName;
	/** 日志时间 */
	protected Date logTime;
	/** 日志类别 */
	protected String logType;
	/** 日志内容 */
	protected String content;
	/** 操作结果 */
	protected String result;
	/** IP地址 */
	protected String ipAddr;
	
	protected Date curDate;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getCurDate() {
		return curDate;
	}
	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}
	public long getSysLogId() {
		return sysLogId;
	}
	public void setSysLogId(long sysLogId) {
		this.sysLogId = sysLogId;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	
}
