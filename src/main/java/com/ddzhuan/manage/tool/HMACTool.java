package com.ddzhuan.manage.tool;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 2018-11-08
 * @author wyp
 *	
 */
public class HMACTool {

	public static String encodeHmacSHA256ToBase64String(String content, String secretKey){
		return Base64.encodeBase64String(encodeHmacSHA256(content, secretKey));
	}
	
	public static byte[] encodeHmacSHA256(String content, String secretKey){
		byte[] code = null;
		try{
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			code = sha256_HMAC.doFinal(content.getBytes("UTF-8"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return code;
	}
	
	
}
