package com.ddzhuan.manage.tool.yopoint;

import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.tool.HttpPostTool;
import com.ddzhuan.manage.tool.MD5Tool;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

public class YoPointApi {

	private String appId = "952284290560";

	private String url = "https://api.yopoint.com/api/gateway/index";

	private String appSecret = "f0624d45a11e8c0d4f6a6c7052693b8c";

	protected final Log log = LogFactory.getLog(YoPointApi.class);

	public static final String YOPOINTURL = "https://c.yopoint.com";

	public final static List<YoPointConfig> OPERATOR_LIST = Lists.newArrayList(
			new NingLianYoPointConfig(),
			new NanKangYoPointConfig(),
			new DiYiYiYaoYoPointConfig(),
			new CigaretteYoPointConfig()
	);

	public YoPointApi() {

	}

	public YoPointApi(YoPointConfig config) {
		this.appId = config.getAppId();
		this.url = config.getUrl();
		this.appSecret = config.getAppSecret();
	}

	public String getUrl() {
		return url;
	}


	/**
	 * 根据运营商ID获取该运营商实例的API
	 * @param oid 运营商ID
	 * @return 运营商API
	 */
	public static Optional<YoPointApi> getYoPointByOID(String oid){
		AtomicReference<YoPointApi> apiAtomicReference = new AtomicReference<>();
		Optional<YoPointConfig> optionalYoPointConfig = OPERATOR_LIST.stream()
				.filter(v -> StringUtils.equals(v.getOid(), oid))
				.findAny();
		optionalYoPointConfig.ifPresent(yoPointConfig -> {
			YoPointApi yoPointApi = new YoPointApi(yoPointConfig);
			apiAtomicReference.set(yoPointApi);
		});
		return Optional.ofNullable(apiAtomicReference.get());
	}

	/**
	 * 根据运营商appId获取该运营商实例的API
	 * @param appId 运营商 appId
	 * @return 运营商API
	 */
	public static Optional<YoPointApi> getYoPointByAppID(String appId){
		AtomicReference<YoPointApi> apiAtomicReference = new AtomicReference<>();
		Optional<YoPointConfig> optionalYoPointConfig = OPERATOR_LIST.stream()
				.filter(v -> StringUtils.equals(v.getAppId(), appId))
				.findAny();
		optionalYoPointConfig.ifPresent(yoPointConfig -> {
			YoPointApi yoPointApi = new YoPointApi(yoPointConfig);
			apiAtomicReference.set(yoPointApi);
		});
		return Optional.ofNullable(apiAtomicReference.get());
	}


	private Map<String, Object> getParamsMap(String api, String bizContent){
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		Long timestamp = System.currentTimeMillis();
		params.put("appid", appId);
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
	 * 分页获取订单信息
	 * <pre>
	 *
	 * </pre>
	 * @param page 获取第N页
	 * @param startTime 开始时间戳（精确到秒）
	 * @param endTime 结束时间戳（精确到秒）
	 * @return result
	 * @throws Exception 异常
	 */
	public String getOrderList(Integer page, Integer startTime, Integer endTime) throws Exception {
		JSONObject json = new JSONObject();
		json.put("Page",page);
		json.put("StartTime",startTime);
		json.put("EndTime",endTime);
		Map<String, Object> params = getParamsMap("consumer.order.list", json.toJSONString());
		return doHttpRequest(url, params);
	}

	/**
	 * 根据商品Token获取订单
	 * <pre>
	 *     错误代码：
	 *     以下格式为 error_msg ：错误描述
	 *     MISSING_PARAMS_TOKEN ：缺少参数:Token
	 *     ERR_NONE_STOCK ：错误:商品存库
	 *     ERR_NONE_COSTPRICE ：错误:商品成本价不正确
	 *     ERR_USER_CREATE_FAIL：错误:用户创建失败
	 *     ERR_ORDER_CREATE_FAIL ：错误:订单创建失败
	 * </pre>
	 * @param token 商品Token
	 * @param openId 第三方用户id，确保用户id唯一
	 * @return json格式字符串
	 * @throws Exception 异常信息
	 */
	public String createOrder(String token, String openId) throws Exception {
		JSONObject json = new JSONObject();
		json.put("Token", token);
		json.put("OpenID", openId);
		Map<String, Object> params = getParamsMap("consumer.order.create", json.toJSONString());
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
	 * @param tid
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

	/**
	 * 获取设备列表（每页输出10条记录）
	 * @param page	获取第N页
	 * @return      data
	 */
	public String getTerminalList(Integer page) {
		// biz_content
		JSONObject json = new JSONObject();
		json.put("Page", page);
		// all request param
		Map<String, Object> params = getParamsMap("terminal.terminal.list", json.toJSONString());
		// send request
		return doHttpRequest(url, params);
	}

	/**
	 * 根据设备ID查询设备虚拟货道配置
	 * <pre>
	 *     错误代码：
	 *     以下格式为 error_msg ：错误描述
	 *     MISSING_PARAMS_TID ：缺少参数:TID
	 *     ERR_TERMINAL_NOT_EXIST ：设备不存在
	 * </pre>
	 * @param tid 设备id
	 * @return result
	 * @throws Exception 异常信息
	 */
	public String getTerminalProductLayout(String tid) throws Exception {
		JSONObject json = new JSONObject();
		json.put("TID", tid);
		Map<String, Object> params = getParamsMap("terminal.product.layout.get", json.toJSONString());
		return doHttpRequest(url, params);
	}

	/**
	 * 获取商品分类树列表
	 * @return data
	 */
	public String getProductCategoryList() {
		// biz_content
		JSONObject json = new JSONObject();
		// all request param
		Map<String, Object> params = getParamsMap("product.category.list", json.toJSONString());
		// send request
		return doHttpRequest(url, params);
	}

	/**
	 * 新增商品接口
	 * <pre>
	 *     商品新增接口，对于同一运营商相同名称的商品视为同一商品，友朋系统会自动分配内部条形码
	 *     注意：
	 *      1、商品有国际条形码必须使用国际条形码
	 *      2、对于无条形码商品，友朋会自动分配一个内部码
	 * </pre>
	 * @param barCode 商品条码 （非必传）
	 * @param name 商品名称
	 * @param categoryId 商品分类id
	 * @param manufacturer 商品厂商
	 * @param imageUrl 商品图片 高清图片（1000*1000像素）
	 * @param imageFixWidthUrl 商品固定宽图片 高清图片（实际厘米数*100像素图）
	 * @param colSpan 商品占位 1/2
	 * @param payExtend 第三支付扩展参数 （非必传）
	 * @return data
	 */
	public String createProduct(String barCode, String name, String categoryId, String manufacturer,
										String imageUrl, String imageFixWidthUrl, int colSpan, String payExtend) {
		// biz_content
		JSONObject json = new JSONObject();
		json.put("BarCode", barCode);
		json.put("Name", name);
		json.put("CategoryID", categoryId);
		json.put("Manufacturer", manufacturer);
		json.put("ImageUrl", imageUrl);
		json.put("ImageFixWidthUrl", imageFixWidthUrl);
		json.put("ColSpan", colSpan);
		json.put("PayExtend", payExtend);
		// all request param
		Map<String, Object> params = getParamsMap("product.operators.create", json.toJSONString());
		// send request
		return doHttpRequest(url, params);
	}


	/**
	 * 运营商开启了第三方支付方式后,可以通过此接口配置第三方支付配置的信息
	 * @param payUrl 支付跳转地址 (必填)
	 * @param serverUrl  回掉通知地址 (必填)
	 * @param singKey	签名密钥
	 * @param subPayType 支付方式整形数组 1=微信,2=支付宝,901=淘宝,902=天猫
	 * @return data
	 */
	public String setThirdPay(String payUrl, String serverUrl, String singKey, List<String> subPayType){
		JSONObject json = new JSONObject();
		json.put("PayUrl", payUrl);
		json.put("ServerUrl", singKey);
		json.put("SignKey", singKey);
		json.put("SubPayType", subPayType);
		Map<String, Object> params = getParamsMap("operators.config.thirdpay.set", json.toJSONString());
		return doHttpRequest(url, params);
	}

	/**
	 * 获取第三方支付配置信息
	 * @return data
	 */
	public String getThirdPay(){
		JSONObject json = new JSONObject();
		Map<String, Object> params = getParamsMap("operators.config.thirdpay.get", json.toJSONString());
		return doHttpRequest(url, params);
	}

}