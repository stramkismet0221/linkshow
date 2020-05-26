package com.ddzhuan.manage.tool.yopoint;

public class NanKangYoPointConfig implements YoPointConfig {

	public String appId = "952284290560";
	
	private String url = "https://api.yopoint.com/api/gateway/index";

	private String appSecret = "f0624d45a11e8c0d4f6a6c7052693b8c";

	private String oid = "5c7755c07b78200011a9376e";
	
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
