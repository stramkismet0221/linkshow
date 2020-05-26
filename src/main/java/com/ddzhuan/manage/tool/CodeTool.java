package com.ddzhuan.manage.tool;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeTool {
	/**
	 * 获取6位随机密码
	 * 
	 * @return
	 */
	public static String getRandomCode() {
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
