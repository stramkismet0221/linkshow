package com.ddzhuan.manage.common.param;

public class UserLoginResultInfo extends BaseResultInfo {
	protected long userId;
	protected long tick;
	protected String ocode;
	
	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getTick() {
		return tick;
	}
	public void setTick(long tick) {
		this.tick = tick;
	}
	
	
}
