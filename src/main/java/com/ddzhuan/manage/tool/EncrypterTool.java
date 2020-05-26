package com.ddzhuan.manage.tool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

import org.apache.commons.codec.binary.Base64;



public class EncrypterTool {
	private static final String HEX_STRING = "0123456789ABCDEF";
	
	private static char[] digits = {'0','1','2','3','4','5','6','7','8','9',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
			'q','r','s','t','u','v','w','x','y','z'};
	
	private static Map<String,Long> digitsMap = new HashMap<String, Long>(){{  
			put("0",0l);
			put("1",1l);
			put("2",2l);
			put("3",3l);
			put("4",4l);
			put("5",5l);
			put("6",6l);
			put("7",7l);
			put("8",8l);
			put("9",9l);
			put("a",10l);
			put("b",11l);
			put("c",12l);
			put("d",13l);
			put("e",14l);
			put("f",15l);
			put("g",16l);
			put("h",17l);
			put("i",18l);
			put("j",19l);
			put("k",20l);
			put("l",21l);
			put("m",22l);
			put("n",23l);
			put("o",24l);
			put("p",25l);
			put("q",26l);
			put("r",27l);
			put("s",28l);
			put("t",29l);
			put("u",30l);
			put("v",31l);
			put("w",32l);
			put("x",33l);
			put("y",34l);
			put("z",35l);
	}};
	
	private static Long radix = 36l;
	
	public static String translate10To36(Long num){
		String rs = "";
		char[] invs = new char[6];
		int clen = 0;
		while(num > 0){
			Long lrs = (num % radix);
			rs += digits[lrs.intValue()];
			num = num / radix;
		}
		return rs;
	}
	
	public static String translate10To36(BigInteger bigInt){
		String rs = "";
		char[] invs = new char[6];
		int clen = 0;
		BigInteger zero = new BigInteger("0");
		BigInteger trx = new BigInteger(radix.toString());
		while(bigInt.compareTo(zero) > 0){
			BigInteger mv = bigInt.mod(trx);
			rs += digits[mv.intValue()];
			bigInt = bigInt.divide(trx);
		}
		return rs;
	}
	
	public static long translate36To10(String num36){
		long rs = 0l;
		String[] numArr = num36.split("");
		int p = 0;
		for(String s : numArr){
			System.out.println(s);
			if(!"".equals(s)){
				rs += digitsMap.get(s) * Math.pow(radix,p);
				p++;
			}
		}
		return rs;
	}
	
	
	public static String decode(String s){
		return decode(s,"UTF-8");
	}
	
	public static String decode(String s, String charset) {
		int length = s.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; ++i) {
        	int index = i * 2;
        	int i1 = HEX_STRING.indexOf(s.substring(index, index+1));
        	int i2 = HEX_STRING.indexOf(s.substring(index+1, index+2));
            byte b = (byte)((i1 << 4) + i2);
            bytes[i] = b;
        }

		byte[] base64Bytes = Base64.decodeBase64(bytes);
        length = base64Bytes.length;
        byte[] decodedBytes = new byte[length];
		for (int i = 0; i < length; ++i) {
			byte b = base64Bytes[i];
			b -= (((i + length) << 1) & 0xFF);
			b += (((i + length) << 2) & 0xFF);
			decodedBytes[i] = b;
		}

        String ret = null;
        if (Charset.isSupported(charset)) {
        	try {
				ret = new String(decodedBytes, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				ret = new String(decodedBytes);
			}
        } else {
        	ret = new String(decodedBytes);
        }
        
		return ret;
	}
	
	public static String encode(String s){
		return encode(s,"UTF-8");
	}
	
	public static String encode(String s, String charset) {
		byte[] bytes = null;
		if (Charset.isSupported(charset)) {
			try {
				bytes = s.getBytes(charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				bytes = s.getBytes();
			}
		} else {
			bytes = s.getBytes();
		}
		
		int length = bytes.length;
		byte[] encodedBytes = new byte[length];
		for (int i = 0; i < length; ++i) {
			byte b = bytes[i];
			b += (((i + length) << 1) & 0xFF);
			b -= (((i + length) << 2) & 0xFF);
			encodedBytes[i] = b;
		}
		
		byte[] base64Bytes =Base64.encodeBase64(encodedBytes);
		String ret = bytesToHex(base64Bytes);
		return ret;
	}
	
	private static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_STRING.charAt(v >>> 4);
	        hexChars[j * 2 + 1] = HEX_STRING.charAt(v & 0x0F);
	    }
	    return new String(hexChars);
	}
	
	public static String compressData(String data) {  
	    ByteArrayOutputStream bos;  
	    DeflaterOutputStream zos;  
	    try {  
	        bos = new ByteArrayOutputStream();  
	        zos = new DeflaterOutputStream(bos);  
	        zos.write(data.getBytes());  
	        zos.close();  
	        return new String(Base64.encodeBase64(bos.toByteArray()));  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	    return null;  
	}  
	
    /** 
     * zlib解压+base64 
     */  
    public static String decompressData(String encdata) {  
        try {  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            InflaterOutputStream zos = new InflaterOutputStream(bos);  
            zos.write(Base64.decodeBase64(encdata.getBytes()));  
            zos.close();  
            return new String(bos.toByteArray());  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return null;  
    }  
	
	public static void main(String[] args)
	{
		//类型(0:积分，1:现金)¤操作(0:查询,1更新)¤用户编号¤更新金额（小于0扣除，大于0新增）¤校验金额¤操作来源（对应mysql中）
		System.out.println("======加密========");
		String splitchar="¤";
		String str="1¤1¤15322"+splitchar+"-1"+splitchar+"67"+splitchar+"appmarket";
		//String str="1¤0¤15322"+splitchar+"0"+splitchar+"0"+splitchar+"appmarket";
		System.out.println("原始数据:"+str+"  splitchar="+EncrypterTool.encode(splitchar));
		String md5str=MD5Tool.MD5Encode(str);
		System.out.println("Md5验证:"+md5str);
		String encStr=EncrypterTool.encode(str);
		System.out.println("加密后数据:"+encStr);
		System.out.println("======解密========");
		String decStr=EncrypterTool.decode(encStr);
		System.out.println("解密后:"+decStr);
		String[] strData=decStr.split(splitchar);
		System.out.println(strData.length);
		String decMd5=MD5Tool.MD5Encode(decStr);
		System.out.println("Md5验证:"+decMd5);
	}
}
