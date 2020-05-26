package com.ddzhuan.manage.tool.shandwan;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class SdwanTool {
	

	Logger log = Logger.getLogger(SdwanTool.class);
	
	private static final String API_URL = "https://h5gm2.shandw.com/open/channel/";

	private static final String SELECTKEY = "d7c227b7856f4cc69ab28ffb5a0acc1a";
	
	private static final String SELECTACCOUNT="nkwl@nankang.com.cn";
	
	private static final String KEY = "sdwt7rSXiK2ArAD0ogfvZs3BkAYTDdQQ";
	
	private static final String CHANNEL="12141";
	
	
	/**
	 * 闪电玩登录
	 * @param uid
	 * @param nick
	 * @param avatar
	 * @param sex
	 * @return
	 * @throws Exception
	 */
	public String login(String uid, String nick, String  avatar,Integer sex)throws Exception {
		StringBuffer param = new StringBuffer();
		String time=String.valueOf(System.currentTimeMillis()/1000);
		param.append("openid=" + uid);
		param.append("&nick=" + nick);
		param.append("&avatar=" + avatar);
		param.append("&sex=" + sex);
		param.append("&phone=" +"");
		param.append("&time=" + time);
		param.append("&channel=" + CHANNEL);
		String a="channel="+CHANNEL+"&openid="+uid+"&time="+time+"&nick="+nick+"&avatar="+avatar+"&sex="+sex+"&phone="+""+"";
		String sign = DigestUtils.md5Hex(a+KEY);
		param.append("&sign=" + sign);
		String url ="http://www.shandw.com/auth/?" + param.toString();
		return url;
	}
	
	/**
	 * 查询支付信息
	 * @param page
	 * @param pageSize
	 * @param stime
	 * @param etime
	 * @return
	 * @throws Exception
	 */
	public String queryPayByChannel(Integer page, Integer pageSize, Long stime,Long etime)throws Exception {
		StringBuffer param = new StringBuffer();
		String time=String.valueOf(System.currentTimeMillis());
		param.append("account=" + SELECTACCOUNT);
		param.append("&channel=" + CHANNEL);
		param.append("&page=" + page);
		param.append("&pageSize=" + pageSize);
		param.append("&stime=" +stime);
		param.append("&sec=" + time);
		param.append("&etime=" + etime);
		String a="account="+SELECTACCOUNT+"&channel="+CHANNEL+"&sec="+time+"&key="+SELECTKEY+"";
		String sign = DigestUtils.md5Hex(a);
		param.append("&sign=" + sign);
		String url =API_URL+"queryPayByChannel?" + param.toString();
		return new String(url.getBytes("ISO-8859-1"),"UTF-8");
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String queryGameHistoryByUser(String userId)throws Exception {
		StringBuffer param = new StringBuffer();
		String time=String.valueOf(System.currentTimeMillis());
		param.append("account=" + SELECTACCOUNT);
		param.append("&channel=" + CHANNEL);
		param.append("&sec=" + time);
		param.append("&userId=" + userId);
		String a="account="+SELECTACCOUNT+"&channel="+CHANNEL+"&sec="+time+"&key="+SELECTKEY+"";
		String sign = DigestUtils.md5Hex(a);
		param.append("&sign=" + sign);
		String url =API_URL+"queryGameHistoryByUser?" + param.toString();
		return new String(url.getBytes("ISO-8859-1"),"UTF-8");
	}

}
