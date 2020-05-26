package com.ddzhuan.manage.tool.weixin;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.tool.HttpPostTool;

import java.net.URLEncoder;

/**
 * 获取微信数据工具
 * @author wyp
 * 2019-01-18
 *
 */
public class WeixinTool {
	
	private static Logger log = Logger.getLogger(WeixinTool.class);
	
	private WxAppConfig appConfig;
	
	public WeixinTool(WxAppConfig appConfig){
		this.appConfig = appConfig;
	}
	
	/***正式微信地址*/
	//用户授权接口
	private static String authorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
	
	//获取用户微信accessToken
	private static String accTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";

	//获取微信用户信息
	private static String userinfoUrl = "https://api.weixin.qq.com/sns/userinfo";

	//refresh_token 直接获取用户信息
	private static String refreshAccTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	
	//获取基础token
	private static String baseTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

	//根据基础token获取用户信息
	private static String userinfoByBaseTokenUrl = "https://api.weixin.qq.com/cgi-bin/user/info";

	/***开发环境微信地址*/
//	//用户授权接口
//	private static String authorizeUrl = "http://127.0.0.1:8080/wdapp/wxtest/authorize";
//		
//	//获取用户微信accessToken
//	private static String accTokenUrl = "http://127.0.0.1:8080/wdapp/wxtest/uinfo";
//
//	//获取微信用户信息
//	private static String userinfoUrl = "http://127.0.0.1:8080/wdapp/wxtest/uinfo";
//
//	//refresh_token 直接获取用户信息
//	private static String refreshAccTokenUrl = "http://127.0.0.1:8080/wdapp/wxtest/uinfo";
//		
//	//获取基础token
//	private static String baseTokenUrl = "http://127.0.0.1:8080/wdapp/wxtest/uinfo";
//
//	//根据基础token获取用户信息
//	private static String userinfoByBaseTokenUrl = "http://127.0.0.1:8080/wdapp/wxtest/uinfo";
	
	/**
	 * 静默授权url，只能获取openid
	 * @param redirectUrl
	 * @param state
	 * @return
	 */
	public String getWxAuthBaseUrl(String redirectUrl, String state){
		String url = "";
		try{
			url = authorizeUrl + "?appid=" + appConfig.getAppId()
					+ "&redirect_uri=" + URLEncoder.encode(redirectUrl, "UTF-8") 
					+ "&response_type=code&scope=snsapi_base&state=" + state + "#wechat_redirect";
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return url;
	}
	
	public String getWxAuthUserInfoUrl(String redirectUrl, String state){
		String url = "";
		try{
			url = authorizeUrl + "?appid=" + appConfig.getAppId()
					+ "&redirect_uri=" + URLEncoder.encode(redirectUrl, "UTF-8") 
					+ "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return url;
	}
	
	/**
	 * 微信授权获取用户信息
	 * 要获取用户unionid首先要开通微信公众号相关获取用户信息权限，然后必须将公众号与开发平台账号绑定。
	 * @param code
	 * @return JSONObject
	 * 返回json中包含refresh_token，可缓存，refresh_token在有效期内可获取微信用户信息（不用授权即）
	 */
	public JSONObject getWxUserBase(String code){
		if(code == null || "".equals(code)){
			return null;
		}
		HttpPostTool hp = new HttpPostTool();
				
		//获取用户微信accessToken
		String aturl = accTokenUrl + "?appid=" + appConfig.getAppId() + "&secret=" + appConfig.getSecret() + "&code=" + code + "&grant_type=authorization_code";
		String result = hp.sslGet(aturl);
		log.info("accToken>>>" + result);
		JSONObject json = JSONObject.parseObject(result);
		String accToken = json.getString("access_token");
		String openid = json.getString("openid");
		String refreshToken = json.getString("refresh_token");
		if(accToken == null || "".equals(accToken)){
			return null;
		}
//		{"access_token":"ACCESS_TOKEN",
//		"expires_in":7200,
//		"refresh_token":"REFRESH_TOKEN",
//		"openid":"OPENID",
//		"scope":"SCOPE"}
		
		json.put("refresh_token", refreshToken);
		return json;
	}
	
	/**
	 * 微信授权获取用户信息
	 * 要获取用户unionid首先要开通微信公众号相关获取用户信息权限，然后必须将公众号与开发平台账号绑定。
	 * @param code
	 * @return JSONObject
	 * 返回json中包含refresh_token，可缓存，refresh_token在有效期内可获取微信用户信息（不用授权即）
	 */
	public JSONObject getWxUserInfo(String code){
		if(code == null || "".equals(code)){
			return null;
		}
		HttpPostTool hp = new HttpPostTool();
				
		//获取用户微信accessToken
		String aturl = accTokenUrl + "?appid=" + appConfig.getAppId() + "&secret=" + appConfig.getSecret() + "&code=" + code + "&grant_type=authorization_code";
		String result = hp.sslGet(aturl);
		log.info("accToken>>>" + result);
		JSONObject json = JSONObject.parseObject(result);
		String accToken = json.getString("access_token");
		String openid = json.getString("openid");
		String refreshToken = json.getString("refresh_token");
		if(accToken == null || "".equals(accToken)){
			return null;
		}
//		{"access_token":"ACCESS_TOKEN",
//		"expires_in":7200,
//		"refresh_token":"REFRESH_TOKEN",
//		"openid":"OPENID",
//		"scope":"SCOPE"}
		
		//获取微信用户信息
		String uiurl = userinfoUrl + "?access_token=" + accToken + "&openid=" + openid + "&lang=zh_CN";
		result = hp.sslGet(uiurl);
		log.info("userinfo>>>" + result);
		json = JSONObject.parseObject(result);
		if(json.getString("openid") == null || "".equals(json.getString("openid"))){
			return null;
		}
//		{"openid":" OPENID",
//		"nickname": NICKNAME,
//		"sex":"1",
//		"province":"PROVINCE"
//		"city":"CITY",
//		"country":"COUNTRY",
//		"headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
//		"privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
//		"unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"}
		
		//获取基础token
//		String baseTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
//		result = hp.sslGet(tokenUrl);
//		json = JSONObject.parseObject(result);
//		log.info("userinfo>>>" + result);
//		String token = json.getString("access_token");
//		
		//根据基础token获取用户信息
//		String userinfoByBaseTokenUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openid + "&lang=zh_CN";
//		result = hp.sslGet(userinfoUrl);
//		json = JSONObject.parseObject(result);
//		log.info("userinfo>>>" + result);
//		if(json.getString("openid") == null || "".equals(json.getString("openid"))){
//			return null;
//		}
//		{"subscribe": 1, 
//		  "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
//		    "nickname": "Band", 
//		    "sex": 1, 
//		    "language": "zh_CN", 
//		    "city": "广州", 
//		    "province": "广东", 
//		    "country": "中国", 
//		    "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
//		    "subscribe_time": 1382694957,
//		    "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
//		    "remark": "",
//		    "groupid": 0,
//		    "tagid_list":[128,2],
//		    "subscribe_scene": "ADD_SCENE_QR_CODE",
//		    "qr_scene": 98765,
//		   "qr_scene_str": ""}
		
		json.put("refresh_token", refreshToken);
		return json;
	}
	
	/**
	 * 通过refresh_token获取微信用户信息
	 * refresh_token需要通过首次授权并缓存微信返回的refresh_token，注意refresh_token的有效时间，如果refresh_token过期需要重新授权
	 * @param request
	 * @return JSONObject
	 * 返回json中包含refresh_token，可缓存，refresh_token在有效期内可获取微信用户信息（不用授权即）
	 */
	public JSONObject getWxUserInfoRefresh(String refreshToken){
		HttpPostTool hp = new HttpPostTool();
		
		//如果用户session中共有refresh_token 直接获取用户信息
		if(refreshToken == null || "".equals(refreshToken)){
			return null;
		}
		//refresh_token 直接获取用户信息
		String ratUrl = refreshAccTokenUrl + "?appid=" + appConfig.getAppId() + "&grant_type=refresh_token&refresh_token=" + refreshToken;
//		log.info("refreshAccTokenURL>>> " + ratUrl);
		String result = hp.sslGet(ratUrl);
//		log.info("refreshAccToken>>>" + result);
		JSONObject json = JSONObject.parseObject(result);
		String accToken = json.getString("access_token");
		String openid = json.getString("openid");
		refreshToken = json.getString("refresh_token");
		if(accToken == null || "".equals(accToken)){
			return null;
		}
//		{ "access_token":"ACCESS_TOKEN",
//			"expires_in":7200,
//			"refresh_token":"REFRESH_TOKEN",
//			"openid":"OPENID",
//			"scope":"SCOPE" }
//		}
		
		//获取微信用户信息
		String uiurl = userinfoUrl + "?access_token=" + accToken + "&openid=" + openid + "&lang=zh_CN";
		result = hp.sslGet(uiurl);
//		log.info("userinfo>>>" + result);
		json = JSONObject.parseObject(result);
		if(json.getString("openid") == null || "".equals(json.getString("openid"))){
			return null;
		}
//		{    "openid":" OPENID",
//			" nickname": NICKNAME,
//			"sex":"1",
//			"province":"PROVINCE"
//			"city":"CITY",
//			"country":"COUNTRY",
//			"headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
//			"privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
//			"unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
//			}
		
		json.put("refresh_token", refreshToken);
		return json;
	}
	
}
