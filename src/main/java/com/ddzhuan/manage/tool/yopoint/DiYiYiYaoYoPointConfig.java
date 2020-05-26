package com.ddzhuan.manage.tool.yopoint;

public class DiYiYiYaoYoPointConfig implements YoPointConfig {

	private String appId = "963961635655";
	
	private String url = "https://api.yopoint.com/api/gateway/index";

	private String appSecret = "d1b8147e4524fb2c430c7dea08dc9125";

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
		return oid;
	}

}
