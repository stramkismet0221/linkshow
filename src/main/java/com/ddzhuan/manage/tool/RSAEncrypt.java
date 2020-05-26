package com.ddzhuan.manage.tool;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAEncrypt {
	
//	private Logger log = Logger.getLogger(RSAEncrypt.class);

	// 加解密类
	private Cipher cipher;

	/**
	 * 加密 by Key实例对象
	 */
	public String encrypt(String contentStr, Key publicKey) {
		String afterEncrypt = null;
		try {
			// 加解密类
			cipher = Cipher.getInstance("RSA");// Cipher.getInstance("RSA/ECB/PKCS1Padding");
			// 明文
			byte[] plainText = contentStr.getBytes();
			// 公钥加密
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] enBytes = cipher.doFinal(plainText);
			afterEncrypt = HexUtil.bytes2Hex(enBytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return afterEncrypt;
	}
	
	/**
	 * 加密 by 公钥字符串（从getKeyString获得，或者从数据库中查询公钥字符串）
	 */
	public String encrypt(String contentStr, String publicKey) {
		String afterEncrypt = null;
		try {
			// 加解密类
			cipher = Cipher.getInstance("RSA");
			// 明文
			byte[] plainText = contentStr.getBytes();
			// 公钥加密
			// cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
			byte[] enBytes = cipher.doFinal(plainText);
			afterEncrypt = HexUtil.bytes2Hex(enBytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return afterEncrypt;
	}
	
	/**
	 * 加密 by 公钥字符串（从getKeyString获得，或者从数据库中查询公钥字符串）
	 */
	public String encryptLongString(String contentStr, String publicKey) {
		StringBuffer afterEncrypt = new StringBuffer("");
		try {
			// 加解密类
			cipher = Cipher.getInstance("RSA");
			// 明文
			byte[] data = contentStr.getBytes();
			for(int i = 0; i < contentStr.length(); i += 100){
				byte[] plainText = ArrayUtils.subarray(data, i, i + 100);
				// 公钥加密
				// cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
				byte[] enBytes = cipher.doFinal(plainText);
				afterEncrypt.append(HexUtil.bytes2Hex(enBytes));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return afterEncrypt.toString();
	}

	/**
	 * 解密 by Key实例对象
	 */
	public String decrypt(String contentStr, Key privateKey) {
		byte[] bytes = HexUtil.hex2Bytes(contentStr);
		String afterDecrypt = null;
		// 加解密类
		try {
			cipher = Cipher.getInstance("RSA");// Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] deBytes = cipher.doFinal(bytes);
			afterDecrypt = new String(deBytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return afterDecrypt;
	}
	
	/**
	 * 解密 by 公钥字符串（从getKeyString获得，或者从数据库中查询公钥字符串）
	 */
	public String decrypt(String contentStr, String privateKey) {
		byte[] bytes = HexUtil.hex2Bytes(contentStr);
		String afterDecrypt = null;
		// 加解密类
		try {
			cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
			byte[] deBytes = cipher.doFinal(bytes);
			afterDecrypt = new String(deBytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return afterDecrypt;
	}
	
	/**
	 * 解密 by 公钥字符串（从getKeyString获得，或者从数据库中查询公钥字符串）
	 */
	public String decryptLongString(String contentStr, String privateKey) {
		byte[] data = HexUtil.hex2Bytes(contentStr);
		StringBuffer afterDecrypt = new StringBuffer("");
		// 加解密类
		try {
			cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
			for(int i = 0; i < data.length; i += 128){
				byte[] deBytes = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
				afterDecrypt.append(new String(deBytes));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return afterDecrypt.toString();
	}
	/**
	 * 得到密钥字符串 by Key实例对象（经过base64编码）
	 * 
	 * @return
	 */
	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		return (new BASE64Encoder()).encode(keyBytes);
	}
	
	/**
	 * 得到密钥字符串 by Key.getEncoded() BASE64 byte[]（经过base64编码）
	 * 
	 * @return
	 */
	public static String getKeyString(byte[] keyBytes) throws Exception {
		return (new BASE64Encoder()).encode(keyBytes);
	}
	
	/**
	 * 得到PublicKey实例对象  by Key.getEncoded() BASE64 byte[]
	 * 
	 * @return
	 */
	public PublicKey getPublicKey(byte[] keyBytes) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到PublicKey实例对象 by 公钥字符串（从getKeyString获得，或者从数据库中查询公钥字符串）
	 * 
	 * @return
	 */
	public PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到PrivateKey实例对象  by Key.getEncoded() BASE64 byte[]
	 * 
	 * @return
	 */
	public PrivateKey getPrivateKey(byte[] keyBytes) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	/**
	 * 得到PrivateKey实例对象 by 私钥字符串（从getKeyString获得，或者从数据库中查询公钥字符串）
	 * 
	 * @return
	 */
	public PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	public KeyPair generateKeyPair(int keysize) throws NoSuchAlgorithmException{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 密钥位数
        keyPairGen.initialize(keysize);
        // 密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
	}
	
	
	public static void main(String[] args)throws Exception{
		String pwd="1234567";
		JSONObject json = new JSONObject();
		json.put("username", "cmic");
		json.put("bizcode", "gimclogin");
		json.put("biztime", System.currentTimeMillis());
		pwd = json.toJSONString();
		pwd="2";
		RSAEncrypt rsaEncrypt = new RSAEncrypt();
//		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        // 密钥位数
//        keyPairGen.initialize(1024);
//        // 密钥对
//        KeyPair keyPair = keyPairGen.generateKeyPair();
		KeyPair keyPair = rsaEncrypt.generateKeyPair(512);
        // 公钥
        PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        //用KEY接口对象加解密
        String afterRSAEncrypt = rsaEncrypt.encrypt(pwd, publicKey);
        String afterRSAFecrypt = rsaEncrypt.decrypt(afterRSAEncrypt, privateKey);
        System.out.println("rsa加密后\t" + afterRSAEncrypt);
        System.out.println("rsa解密后:\t" + afterRSAFecrypt);
        
        //用字符串加解密（可用于持久化）
        String publicKeyStr = rsaEncrypt.getKeyString(publicKey.getEncoded());
        String privateKeyStr = rsaEncrypt.getKeyString(privateKey.getEncoded());
        afterRSAEncrypt = rsaEncrypt.encrypt(pwd, publicKeyStr);
        afterRSAFecrypt = rsaEncrypt.decrypt(afterRSAEncrypt, privateKeyStr);
        System.out.println("公钥：" + publicKeyStr);
        System.out.println("私钥：" + privateKeyStr);
        System.out.println("rsa加密后:" + afterRSAEncrypt);
        System.out.println("rsa解密后:" + afterRSAFecrypt);
        System.out.println(afterRSAEncrypt.substring(0, afterRSAEncrypt.length()/2));
		System.out.println(afterRSAEncrypt.substring(afterRSAEncrypt.length()/2));
        System.out.println(MD5Tool.MD5Encode(afterRSAEncrypt.substring(0, afterRSAEncrypt.length()/2)));
		System.out.println(MD5Tool.MD5Encode(afterRSAEncrypt.substring(afterRSAEncrypt.length()/2)));
        
//        publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAqXPhidFshXAczHt/lbTPytH2AEDzcH6W7G0u"+
//        "lI2ScRAd5xzBlv07m2esrUF5ALCAeZ8ifl05BJa8OUo9Geo2kMlV1G+2LkUTeiwdH2GEF5XILvpK"+
//        "DUYgQVRm44aWeTtOK3xdGws1wpPe8QY36jK3324z4KtHEB0JA8z/Ce6O+wIDAQAB”";
//        
//         privateKeyStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAICpc+GJ0WyFcBzMe3+VtM/K0fYA"+
//        "QPNwfpbsbS6UjZJxEB3nHMGW/TubZ6ytQXkAsIB5nyJ+XTkElrw5Sj0Z6jaQyVXUb7YuRRN6LB0f"+
//        "YYQXlcgu+koNRiBBVGbjhpZ5O04rfF0bCzXCk97xBjfqMrffbjPgq0cQHQkDzP8J7o77AgMBAAEC"+
//        "gYBibkEjOOapGWTuA1ov/342wjni88q880FFgVb+HdQ5RppH5jnPuQ2+/mNQbbP7OJXPlDw0jWwQ"+
//        "ZPxUv5eWKy9MvVFy8fqC37k34d3UAhzHZpfL7M02dDJT4+CHtTQ7EB7R6dccRNV3vqFSQeWrOljZ"+
//        "48tM9AR0g/wABevoysAowQJBAMAJlAy12tq0kxQv+LDcvOTfQmgUDSL/sl0aKKrCSkwiTYazuya5"+
//        "O/HRWoucMwzr2mFhy0fCa+NEWhm5Evry4zECQQCrhAwQWFHAbARJ5MndCYDp0zxMxTy2w0IPtRAL"+
//        "lwwyhU8RKqz7wXnHe2hjngMGsg6zJ295LJ55UfA1iIRYDdHrAkEAoWkrnIdaPcvbd/NeV+Ihj0os"+
//        "uehopks/c0vidKy74ERQ33DAVKsLPJUYQxJ2Ql0XgBS/1UAQiMTR6p8J2g4/cQJAEZIzkjEaHvZx"+
//        "8ggITia9GDdVxB+ouqiCSg2omSdeToattPqEQj5t9WvFI/96G+N98gimOOnDSnaEf1d/B25TBQJB"+
//        "AIyYub8Zp6q9wXHw2ohwhEJnA0ie8pDbEvTakpvEsfR/UmtLnGVmEaFOYM4vsmmnuLAt/LDtaztW"+
//        "jm83H+ecGwI=";
//        
//        afterRSAEncrypt = rsaEncrypt.encrypt(pwd, publicKeyStr);
//        afterRSAFecrypt = rsaEncrypt.decrypt(afterRSAEncrypt, privateKeyStr);
//        System.out.println("公钥：" + publicKeyStr);
//        System.out.println("私钥：" + privateKeyStr);
//        System.out.println("rsa加密后：" + afterRSAEncrypt);
//        System.out.println("rsa解密后：" + afterRSAFecrypt);
        }

}