package com.ddzhuan.manage.common.param;

import java.io.Serializable;
import java.util.HashMap;

import javax.naming.spi.DirStateFactory.Result;

public class UserResultInfo implements Serializable{
	protected Long userId;
	
	protected String password;
	
	protected String mobile;
	
	protected String wxUnionId;
	
	protected String qqUnionId;
	
	protected String wbUnionId;
	
	protected String nickName;//昵称
	
	protected Integer regSource;//注册来源
	
	protected String sn;//设备序列号
	
	protected String invitationCode;//用户自己的邀请码
	
	protected String inviter;//邀请人
	
	protected Long ddp;  //ddp
	
	protected Double ddt; //ddt

	// 是否成功默认为false
	protected boolean success = false;
		
	// 返回msg的值默认空
	protected String msg;
		
	//返回注册uid
	protected String uid;
		
	protected HashMap<String,Object> extInfo=new HashMap<String,Object>();
		
	public UserResultInfo(){
		this.success=false;
		this.msg="请检查网络连接。";
	}
	
	
	
	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	public HashMap<String, Object> getExtInfo() {
		return extInfo;
	}



	public void setExtInfo(HashMap<String, Object> extInfo) {
		this.extInfo = extInfo;
	}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWxUnionId() {
		return wxUnionId;
	}

	public void setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
	}

	public String getQqUnionId() {
		return qqUnionId;
	}

	public void setQqUnionId(String qqUnionId) {
		this.qqUnionId = qqUnionId;
	}

	public String getWbUnionId() {
		return wbUnionId;
	}

	public void setWbUnionId(String wbUnionId) {
		this.wbUnionId = wbUnionId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getRegSource() {
		return regSource;
	}

	public void setRegSource(Integer regSource) {
		this.regSource = regSource;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}


	public Long getDdp() {
		return ddp;
	}

	public void setDdp(Long ddp) {
		this.ddp = ddp;
	}



	public Double getDdt() {
		return ddt;
	}



	public void setDdt(Double ddt) {
		this.ddt = ddt;
	}

	
	
	
}
