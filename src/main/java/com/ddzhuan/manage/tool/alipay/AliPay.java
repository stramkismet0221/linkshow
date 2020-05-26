package com.ddzhuan.manage.tool.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;

public class AliPay {

	/// <summary>
	/// 生成URL链接或加密结果
	/// </summary>
	/// <param name="para">参数加密数组</param>
	/// <param name="_input_charset">编码格式</param>
	/// <param name="sign_type">加密类型</param>
	/// <param name="key">安全校验码</param>
	/// <returns>字符串URL或加密结果</returns>

	// GET方式传递参数时请去掉注释
	public static String CreatUrl(String gateway, String[] para, String _input_charset, String sign_type, String key) {

		// 进行排序；
		Arrays.sort(para);

		// 构造待md5摘要字符串 ；

		StringBuilder prestr = new StringBuilder();

		for (int i = 0; i < para.length; i++) {
			if (i == para.length - 1) {
				prestr.append(para[i]);
			} else {
				prestr.append(para[i] + "&");
			}
		}

		prestr.append(key);

		System.out.println(prestr.toString());
		// 生成Md5摘要；
		String sign = MD5Encoder(prestr.toString(), _input_charset);

		// 以下是POST方式传递参数
		// return sign;

		// 以下是GET方式传递参数
		// 构造支付Url；
		StringBuilder parameter = new StringBuilder();
		parameter.append(gateway);
		for (int i = 0; i < para.length; i++) {// UTF-8格式的编码转换
			//System.out.println(para[i].split("=")[0] +">>>"+para[i].split("=")[1]);
			try {
				parameter.append(
						para[i].split("=")[0] + "=" + URLEncoder.encode(para[i].split("=")[1], _input_charset) + "&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		parameter.append("sign=" + sign + "&sign_type=" + sign_type);

		// 返回支付Url；
		return parameter.toString();
	}

	public final static String MD5Encoder(String s, String charset) {
		try {
			byte[] btInput = s.getBytes(charset);
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
