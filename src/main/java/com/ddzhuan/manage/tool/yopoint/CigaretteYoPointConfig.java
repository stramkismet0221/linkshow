package com.ddzhuan.manage.tool.yopoint;

public class CigaretteYoPointConfig implements YoPointConfig {

	private String appId = "969381756010";
	
	private String url = "https://api.yopoint.com/api/gateway/index";

	private String appSecret = "af9cf58970fc8c56901341365b5299e3";

	private String oid = "";
	
	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getAppSecret() {
		return appSecret;
	}

	@Override
	public String getOid() {
		return null;
	}


}
