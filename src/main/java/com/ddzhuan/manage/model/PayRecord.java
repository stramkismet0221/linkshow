package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class PayRecord implements Serializable {
	
	//订单号
	private String cpOrderId;
	
	//渠道id
	private String channel;
	
	//闪电玩方用户id
	private String uid;
	
	//喵星球用户id
	private Long openid;
	
	//闪电玩应用id
	private String appId;
	
	//产品
	private String product;
	
	//支付的金额，分
	private long money;
	
	//支付的时间戳
	private Date time;

	public String getCpOrderId() {
		return cpOrderId;
	}

	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getOpenid() {
		return openid;
	}

	public void setOpenid(Long openid) {
		this.openid = openid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

	
}
