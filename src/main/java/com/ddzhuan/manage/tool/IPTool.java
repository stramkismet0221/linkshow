package com.ddzhuan.manage.tool;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.model.IPAttrData;

public class IPTool {

	/**
	 * 获得真是ip地址
	 * @param request
	 * @return
	 */
	public static String getIPAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15												
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
	
	/**
	 * 
	 * @param ip
	 * @return
	 */
	public static IPAttrData getIPAttrData(String ip){
		 IPAttrData data = null;
		 HttpPostTool httpPostTool = new HttpPostTool();
		 String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="+ip;
		 HashMap<String, Object> params = new HashMap<String, Object>();
//		 params.put("format", "json");
//		 params.put("ip", ip);
		 String result = "";
		 try{
			 result = httpPostTool.get(url);
			 //{"ret":1,"start":-1,"end":-1,"country":"\u4e2d\u56fd","province":"\u4e0a\u6d77","city":"\u4e0a\u6d77","district":"","isp":"","type":"","desc":""}
			 //-1 -2 -3
		 }catch(Exception ex){
			 ex.printStackTrace();
			 result = "";
		 }

		 if(result != null && !"".equals(result)
				 && !"-1".equals(result)
				 && !"-2".equals(result)
				 && !"-3".equals(result)){
			 data = new IPAttrData();
			 JSONObject json = JSON.parseObject(result);
			 data.setCity(json.getString("city"));
			 data.setCountry(json.getString("country"));
			 data.setDesc(json.getString("desc"));
			 data.setDistrict(json.getString("district"));
			 data.setEnd(json.getString("end"));
			 data.setIsp(json.getString("isp"));
			 data.setProvince(json.getString("province"));
			 data.setRet(json.getString("ret"));
			 data.setStart(json.getString("start"));
			 data.setType(json.getString("type"));
		 }
         return data;
	}
	public static Long ip2Long(String ip) {
		if (!Pattern.matches("^(\\d{1,3}\\.){3}\\d{1,3}$", ip)) {
			return -1l;
		}
		String[] ipNums = ip.split("\\.");
		return (Long.parseLong(ipNums[0]) << 24)
				+ (Long.parseLong(ipNums[1]) << 16)
				+ (Long.parseLong(ipNums[2]) << 8)
				+ (Long.parseLong(ipNums[3]));
	}
	
}
