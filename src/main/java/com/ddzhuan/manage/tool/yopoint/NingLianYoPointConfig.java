package com.ddzhuan.manage.tool.yopoint;

public class NingLianYoPointConfig implements YoPointConfig {

	private String appId = "963356198980";
	
	private String url = "https://api.yopoint.com/api/gateway/index";

	private String appSecret = "0a8dba7abc1727df2bdb6260df70c8fa";

	private String oid = "5ccfe07fbeb2b10012d24d9a";
	
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
