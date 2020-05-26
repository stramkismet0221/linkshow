package com.ddzhuan.manage.common.enums;

/**
 * 注册来源
 * @author wyp
 *
 */
public enum LoginTypeEnum {
	
	QQ(2, "qqplugs"),
	
	WEIBO(3, "weiboplugs"),
	
	WEIXIN(4, "weixinplugs"),
	
	IOS_APP(8, "iosapp"),
	
	ANDROID_APP(9, "androidapp"),
	
	WEB(10, "web"),
	
	;

	private Integer value;
	
	private String code;
	
	private LoginTypeEnum(Integer value, String code){
		this.value = value;
		this.code = code;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



}

	