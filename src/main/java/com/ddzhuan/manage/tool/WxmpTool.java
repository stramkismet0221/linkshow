package com.ddzhuan.manage.tool;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;

/**
 * 微信小程序信息获取
 *
 * @author Administrator
 * @Date 2017年2月16日 11:56:08
 */
public class WxmpTool {

	private static WxmpTool instance = null;

	private WxmpTool(){}

	public static WxmpTool getWxmpTool(){
		if(instance == null){
			instance = new WxmpTool();
		}
		return instance;
	}

	private Logger log = Logger.getLogger(WxmpTool.class);

	public JSONObject getWxmpxSessionKey(String appid, String secret, String jscode){
		String wxUrl ="https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + jscode + "&grant_type=authorization_code";
		HttpPostTool httpPost = new HttpPostTool();
		String wxResult = httpPost.get(wxUrl);
		JSONObject json = JSONObject.parseObject(wxResult);
		log.info("微信小程序登陆返回值：>>>>>>>>>>>>>" + wxResult);
		return json;
	}

	/**
	 * 解密用户敏感数据获取用户信息
	 *
	 * @param sessionKey
	 *            数据进行加密签名的密钥
	 * @param encryptedData
	 *            包括敏感数据在内的完整用户信息的加密数据
	 * @param iv
	 *            加密算法的初始向量
	 * @return
	 */
	public JSONObject getWxUserInfo(String encryptedData, String sessionKey, String iv) throws Exception{
		JSONObject result = null;
		log.info("WxUserInfo>>>> "+encryptedData+"sessionKey"+sessionKey+"iv"+iv );
		byte[] resultByte = AESTool.decrypt(Base64.decode(encryptedData),
				Base64.decode(sessionKey),
				Base64.decode(iv));
		if (null != resultByte && resultByte.length > 0) {
			String userInfo = new String(resultByte, "UTF-8");
			log.info("decode>>>> "+userInfo);
			result = JSONObject.parseObject(userInfo);
			log.info(3);
		}
		return result;

	}

//	static public void main(String[] arg) throws Exception {
//		String appId = "wx4f4bc4dec97d474b";
//		String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
//		String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM" + "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS"
//				+ "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+" + "3hVbJSRgv+4lGOETKUQz6OYStslQ142d"
//				+ "NCuabNPGBzlooOmB231qMM85d2/fV6Ch" + "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6"
//				+ "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw" + "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn"
//				+ "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs" + "8LOddcQhULW4ucetDf96JcR3g0gfRK4P"
//				+ "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB" + "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns"
//				+ "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd" + "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV"
//				+ "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG" + "20f0a04COwfneQAGGwd5oa+T8yO5hzuy" + "Db/XcxxmK01EpqOyuxINew==";
//		String iv = "r7BXXKkLb8qrSNn05n0qiA==";
//
//		WxmpTool.getWxmpTool().getWxUserInfo(encryptedData, sessionKey, iv).toJSONString();
////		System.out.println();
//	}

}