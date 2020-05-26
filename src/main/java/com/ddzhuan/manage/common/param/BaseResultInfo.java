package com.ddzhuan.manage.common.param;

import java.io.Serializable;
import java.util.HashMap;

public class BaseResultInfo implements Serializable {
	
	// 是否成功默认为false
	protected boolean success = false;
	
	// 返回msg的值默认空
	protected String msg;
	
	protected HashMap<String,Object> extInfo=new HashMap<String,Object>();
	
	public BaseResultInfo(){
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

	public void putExtInfo(String key,Object value){
		extInfo.put(key, value);
	}
	public HashMap<String, Object> getExtInfo() {
		return extInfo;
	}
	public void setExtInfo(HashMap<String, Object> extInfo) {
		this.extInfo = extInfo;
	}

	public static BaseResultInfo doResponse(boolean flag,String msg){
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		baseResultInfo.setSuccess(flag);
		baseResultInfo.setMsg(msg);
		return baseResultInfo;
	}

	public static BaseResultInfo doResponse(boolean flag){
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		baseResultInfo.setSuccess(flag);
		baseResultInfo.setMsg("");
		return baseResultInfo;
	}
}
