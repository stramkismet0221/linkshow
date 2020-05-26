package com.ddzhuan.manage.mysqlmodel;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * dapp 系统日志
 * @author wyp
 *
 */
public class SysLogInfo implements Serializable{
	
	private Long logId;//日志主键
	private Integer logType;//日志类型表主键
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;//日志创建时间
	
	private String serverCode;//服务器编号（配置在服务端，也可以是服务器内网ip地址）
	private String objKey;//影响对象主键
	private String ip;//事件发起方ip地址（如果有的话，如登陆、注册等用户访问）
	private String operatorId;//操作人id
	private Integer operatorType;//1、系统自动；2、用户；3、管理员
	private String actDesc;//事件简述
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date actTime;//事件发生时间
	private String actInfo;//事件数据或详细内容
	private Integer logLevel;//日志级别
	
	
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getServerCode() {
		return serverCode;
	}
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}
	public String getObjKey() {
		return objKey;
	}
	public void setObjKey(String objKey) {
		this.objKey = objKey;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public Integer getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}
	public String getActDesc() {
		return actDesc;
	}
	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}
	public Date getActTime() {
		return actTime;
	}
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
	public Integer getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}
	public String getActInfo() {
		return actInfo;
	}
	public void setActInfo(String actInfo) {
		this.actInfo = actInfo;
	}
}
