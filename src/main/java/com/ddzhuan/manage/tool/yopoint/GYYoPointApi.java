package com.ddzhuan.manage.tool.yopoint;

import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.tool.HttpPostTool;
import com.ddzhuan.manage.tool.MD5Tool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class GYYoPointApi {

	private String url = "https://api.yopoint.com/api/gateway/index";

	private String appSecret = "d1b8147e4524fb2c430c7dea08dc9125";
	
	protected final Log log = LogFactory.getLog(GYYoPointApi.class);
	
	public String getUrl() {
		return url;
	}

	private Map<String, Object> getParamsMap(String api, String bizContent){
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		Long timestamp = System.currentTimeMillis();
		params.put("appid", "963961635655");
		params.put("version", "1.0.0");
		params.put("sign_type", "md5");
		params.put("method", api);
		params.put("biz_content", bizContent);
		params.put("timestamp", timestamp+"");
		
		String sign = getSign(params);
		params.put("sign", sign);
		
		return params;
	}
	
	public String getSign(Map<String, Object> params){
		StringBuilder rpStr = new StringBuilder();
		for (Entry<String, Object> item : params.entrySet()) {
			rpStr.append(item.getKey()).append("=").append((String)params.get(item.getKey())).append("&");
		}
		rpStr.append(appSecret);
		log.info("payNotify>>> rpStr="+rpStr);
		return MD5Tool.MD5Encode(rpStr.toString()).toLowerCase();
	}
	
	public String getSign(Map<String, Object> params, String key){
		StringBuilder rpStr = new StringBuilder();
		for (Entry<String, Object> item : params.entrySet()) {
			rpStr.append(item.getKey()).append("=").append((String)params.get(item.getKey())).append("&");
		}
		rpStr.append(key);
		log.info("payNotify>>> rpStr="+rpStr);
		return MD5Tool.MD5Encode(rpStr.toString()).toLowerCase();
	}
	
	public String doHttpRequest(String url, Map<String, Object> params){
		HttpPostTool http = new HttpPostTool();
		Map<String, Object> headparams = new HashMap<String, Object>();
		headparams.put("Content-Type","application/x-www-form-urlencoded");
		String result = http.post(url, params);
		return result;
	}
	
	public String getVoucherInfo(String voucherID){
		//biz_content
		JSONObject json = new JSONObject();
		json.put("VoucherID",voucherID);
		//all request param
		Map<String, Object> params = getParamsMap("activity.voucher.get", json.toJSONString());
		//send request
		return doHttpRequest(url, params);
	}
	
	public String getVouchercodeInfo(String voucherID, String voucherCode, String encryptCode){
		//biz_content
		JSONObject json = new JSONObject();
		json.put("VoucherID",voucherID);
		json.put("VoucherCode",voucherCode == null? "" : voucherCode);
		json.put("EncryptCode",encryptCode == null? "" : encryptCode);
		//all request param
		Map<String, Object> params = getParamsMap("activity.vouchercode.get", json.toJSONString());
		//send request
		return doHttpRequest(url, params);
	}
	
	public String getVoucherReceiveUrlInapp(String voucherID, String barCode, String openId, String nickName, String returnUrl){		
		JSONObject json = new JSONObject();
		json.put("VoucherID",voucherID);
		json.put("BarCode",barCode);//		BarCode Y 商品条形码
		json.put("OpenID",openId);//		OpenID Y 第三方用户标识
		json.put("NickName",nickName);//		NickName N 第三方用户昵称
		json.put("ReturnUrl",returnUrl);//		ReturnUrl N 跳转地址，用户领取后自动跳转
//		Sex N 性别
//		Province N 第三方用户所在省份
//		City N 第三方用户所在城市
		
		//all request param
		Map<String, Object> params = getParamsMap("activity.voucher.receive.url.inapp", json.toJSONString());
		//send request
		return doHttpRequest(url, params);
	}
	
	public String getVoucherExchange(String code, String tid){
		//biz_content
		JSONObject json = new JSONObject();
		json.put("Code",code);
		json.put("TID",tid);
		//all request param
		Map<String, Object> params = getParamsMap("activity.voucher.exchange", json.toJSONString());
		//send request
		return doHttpRequest(url, params);
	}
	
	/**
	 * 根据订单号获取订单信息
	 * @param receiptNo
	 * @return
	 */
	public String getConsumerOrderByReceiptNo(String receiptNo){
		//biz_content
		JSONObject json = new JSONObject();
		json.put("ReceiptNo",receiptNo);
		//all request param
		Map<String, Object> params = getParamsMap("consumer.order.get", json.toJSONString());
		//send request
		return doHttpRequest(url, params);
	}
	
	
	public String payNotify(String notifyUrl, String receiptNo, String tradeNo, String price, int tradeStatus){
		StringBuilder rpStr = new StringBuilder();
		//biz_content

		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("receipt_no", receiptNo);
		params.put("trade_no", tradeNo);
		params.put("trade_status", tradeStatus+"");
		params.put("trade_rawdata", "{}");
		params.put("timestamp", System.currentTimeMillis()/1000 + "");

		String sign = getSign(params, "f0615d54a22e8c0d5f6a6c7041693b8c");
		log.info("payNotify>>> sign="+sign);
		params.put("sign", sign);
		params.put("price", price);//不参与前面
//		log.info("payNotify>>> receipt_no="+receiptNo);
//		log.info("payNotify>>> trade_no="+tradeNo);
//		log.info("payNotify>>> trade_status="+tradeStatus);
//		log.info("payNotify>>> price="+params.get("price"));
//		log.info("payNotify>>> trade_rawdata="+params.get("trade_rawdata"));
		
		return doHttpRequest(notifyUrl, params);
	}
	
	/**
	 * 根据设备号获取设备信息
	 * @param receiptNo
	 * @return
	 */
	public String getTerminalByTid(String tid){
		//biz_content
		JSONObject json = new JSONObject();
		json.put("TID",tid);
		//all request param
		Map<String, Object> params = getParamsMap("terminal.terminal.get", json.toJSONString());
		//send request
		return doHttpRequest(url, params);
	}
	/**
	 * 根据订单号，更新退款状态
	 * @param receiptNo
	 * @param userRefundsStatus
	 * @return
	 */
	public String updateRefundStatus(String receiptNo,int userRefundsStatus){
		//biz_content
		JSONObject json = new JSONObject();
		json.put("ReceiptNo",receiptNo);
		json.put("UserRefundsStatus",userRefundsStatus);
		//all request param
		Map<String, Object> params = getParamsMap("consumer.order.refunds.update", json.toJSONString());
		//send request
		return doHttpRequest(url, params);
	}
	
	
	
}
