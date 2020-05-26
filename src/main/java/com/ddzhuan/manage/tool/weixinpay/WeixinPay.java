package com.ddzhuan.manage.tool.weixinpay;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.ddzhuan.manage.tool.MD5Tool;

public class WeixinPay {

	private static String appid = "wxaf5ca6dac073c671";
	private static String mchid = "1254161001";
	private static String key = "8dKjl65987DfH46jv89Mycl6K509bVMd";

	public static String createUnifiedorderUrl(String body, String out_trade_no, String total_fee,
			String spbill_create_ip, String notify_url, String trade_type) {
		StringBuilder sb = new StringBuilder("https://api.mch.weixin.qq.com/pay/unifiedorder?");
		// 公众账号ID appid 是 String(32) wxd678efh567hg6787
		// 微信支付分配的公众账号ID（企业号corpid即为此appId）
		// 商户号 mch_id 是 String(32) 1230000109 微信支付分配的商户号
		// 随机字符串 nonce_str 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
		// 随机字符串，长度要求在32位以内。推荐随机数生成算法
		// 签名 sign 是 String(32) C380BEC2BFD727A4B6845133519F3AD6
		// 通过签名算法计算得出的签名值，详见签名生成算法
		// 商品描述 body 是 String(128) 腾讯充值中心-QQ会员充值 商品简单描述，该字段请按照规范传递，具体请见参数规定
		// 商户订单号 out_trade_no 是 String(32) 20150806125346
		// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
		// 标价金额 total_fee 是 Int 88 订单总金额，单位为分，详见支付金额
		// 终端IP spbill_create_ip 是 String(64) 123.12.12.123
		// 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
		// 通知地址 notify_url 是 String(256) http://www.weixin.qq.com/wxpay/pay.php
		// 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
		// 交易类型 trade_type 是 String(16) JSAPI JSAPI -JSAPI支付 NATIVE -Native支付
		// APP -APP支付 说明详见参数规定

		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("appid", appid);
		map.put("mch_id", mchid);
		map.put("nonce_str", "1K1111ILTKCH16CQ475SI8ZNMTM67VS");
		map.put("body", body);
		map.put("out_trade_no", out_trade_no);
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("notify_url", notify_url);
		map.put("trade_type", trade_type);

		StringBuilder rpStr = new StringBuilder();
		for (Entry<String, String> item : map.entrySet()) {
			rpStr.append(item.getKey()).append("=").append((String) map.get(item.getKey())).append("&");
		}
		rpStr.append("key=" + key);
		String sign = MD5Tool.MD5Encode(rpStr.toString()).toUpperCase();
		map.put("sign", sign);

		return sb.toString();
	}

	public String doUnifiedorder(String body, String out_trade_no, String total_fee, String spbill_create_ip,
			String notify_url, String trade_type) throws Exception {
		MyConfig config = new MyConfig();
		WXPay wxpay = new WXPay(config);

		Map<String, String> data = new HashMap<String, String>();
		data.put("body", body);
		data.put("out_trade_no", out_trade_no);
		data.put("device_info", "");
		data.put("fee_type", "CNY");
		data.put("total_fee", total_fee);
		data.put("spbill_create_ip", spbill_create_ip);
		data.put("notify_url", notify_url);
		data.put("trade_type", trade_type); // 此处指定为扫码支付
		// data.put("product_id", "12");

		Map<String, String> resp = wxpay.unifiedOrder(data);
		System.out.println(resp);
		return resp.get("prepay_id");

	}

}
