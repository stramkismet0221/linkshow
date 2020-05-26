package com.ddzhuan.manage.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.YouPengResultInfo;
import com.ddzhuan.manage.model.YouPengOrder;
import com.ddzhuan.manage.service.YPOrderService;
import com.ddzhuan.manage.tool.CacheTool;
import com.ddzhuan.manage.tool.alipay.AliPay;
import com.ddzhuan.manage.tool.alipay.AlipayConfig;
import com.ddzhuan.manage.tool.alipay.AlipayConfigEnum;
import com.ddzhuan.manage.tool.alipay.AlipayNotify;
import com.ddzhuan.manage.tool.alipay.wappay.entites.AlipayResult;
import com.ddzhuan.manage.tool.weixin.NKAppConfig;
import com.ddzhuan.manage.tool.weixin.WeixinTool;
import com.ddzhuan.manage.tool.weixinpay.MyConfig;
import com.ddzhuan.manage.tool.weixinpay.WXPay;
import com.ddzhuan.manage.tool.weixinpay.WXPayConstants;
import com.ddzhuan.manage.tool.weixinpay.WXPayUtil;
import com.ddzhuan.manage.tool.yopoint.YoPointApi;

@Controller
@RequestMapping("/pay")
public class PayPalController implements Serializable {

	private static final long serialVersionUID = 1L;

	protected final Log log = LogFactory.getLog(getClass());

	private int paymentTime = 30;

	@Autowired
	protected SysParam _p;

	@Autowired
	protected CacheTool cache;

	@Autowired
	protected YPOrderService yPOrderService;

	/**
	 *友朋回调详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @param receipt_no
	 * @param return_url
	 * @param notify_url
	 * @param sign
	 * @param timestamp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "orderdetails", method = RequestMethod.GET)
	public String toorderdetail( HttpServletRequest request,
								 HttpServletResponse response, Model model,
								 @RequestParam(required = true) String receipt_no,//友朋业务订单号全局唯一
								 @RequestParam(required = true) String return_url,//同步返回支付结果地址
								 @RequestParam(required = true) String notify_url,//异步回调支付结果通知地址
								 @RequestParam(required = true) String sign,//签名
								 @RequestParam(required = true) int timestamp//时间戳
	) throws Exception {
		String userAgent = request.getHeader("user-Agent").toString();
		log.info("$$$$$$$$$$ orderdetails 友朋详情页 >>>>>>>>>>>>>receipt_no=" + receipt_no);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>return_url=" + return_url);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>notify_url=" + notify_url);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>sign=" + sign);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>timestamp=" + timestamp);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>userAgent=" + userAgent);
		String tid = "";
		try{
			YoPointApi ypApi = new YoPointApi();
			String respStr = ypApi.getConsumerOrderByReceiptNo(receipt_no);
			JSONObject order = JSONObject.parseObject(respStr);
			JSONObject data = order.getJSONObject("data");
			JSONObject product = data.getJSONObject("ProductVO");
			tid = data.getString("TID");
			String PayType=data.getString("PayType");
//			log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>tid=" + tid);
			String str = ypApi.getTerminalByTid(tid);

			JSONObject terminal = JSONObject.parseObject(str);
			JSONObject realter = terminal.getJSONObject("data");
			JSONObject address=realter.getJSONObject("TerminalConfigVO");
			JSONObject address2=address.getJSONObject("PlaceVO");
			String realaddress=address2.getString("City")+address2.getString("District")+address.getString("Address");
			request.setAttribute("title", data.getString("ProductName"));
			DecimalFormat df = new DecimalFormat("0.00");
			request.setAttribute("price", df.format(data.getDouble("Price")/100));
			request.setAttribute("imgurl", product.getString("ImageFixWidthUrl"));
			request.setAttribute("address",realaddress);
			request.setAttribute("receipt_no",receipt_no);
			request.setAttribute("return_url",return_url);
			request.setAttribute("notify_url",notify_url);
			request.setAttribute("sign",sign);
			request.setAttribute("timestamp",timestamp);
		}catch(Exception ex){
			log.error("友朋详情页异常");
			log.error(ex.getMessage(), ex);
			request.setAttribute("msg","系统异常，订单已取消");
			return "/pay/cancel";
		}
		if(!(userAgent.indexOf("Alipay") >= 0 || userAgent.toLowerCase().indexOf("alipay") >= 0
				|| userAgent.indexOf("MicroMessenger") >= 0 || userAgent.toLowerCase().indexOf("micromessenger") >= 0)){
			request.setAttribute("msg","订单无法支付，请使用微信或支付宝扫码");
			return "/pay/cancel";
		}

//		if("5b4ee32bc0e8fe00120c8913".equals(tid) && !"http://test.ddzhuan.cn/wdapp".equals(_p.getWdappURL())){
//			StringBuilder testPayUrl = new StringBuilder();
//			testPayUrl.append("redirect:http://test.ddzhuan.cn/wdapp/pay/orderdetails?receipt_no=").append(receipt_no)
//			.append("&return_url=").append(java.net.URLEncoder.encode(return_url,"UTF-8"))
//			.append("&notify_url=").append(java.net.URLEncoder.encode(notify_url,"UTF-8"))
//			.append("&sign=").append(sign)
//			.append("&timestamp=").append(timestamp);
//			log.info("testPay >>>>>>>>>>>" + testPayUrl);
//			return testPayUrl.toString();
//		}
		return "/pay/gooddetails";
	}

	/**
	 * 自动售货机支付入口
	 * @param request
	 * @param response
	 * @param model
	 * @param receipt_no
	 * @param return_url
	 * @param notify_url
	 * @param sign
	 * @param timestamp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "vendpay", method = RequestMethod.GET)
	public String pay( HttpServletRequest request,
					   HttpServletResponse response, Model model,
					   @RequestParam(required = true) String receipt_no,//友朋业务订单号全局唯一
					   @RequestParam(required = true) String return_url,//同步返回支付结果地址
					   @RequestParam(required = true) String notify_url,//异步回调支付结果通知地址
					   @RequestParam(required = true) String sign,//签名
					   @RequestParam(required = true) int timestamp//时间戳
	) throws Exception {
		String userAgent = request.getHeader("user-Agent").toString();
		log.info("$$$$$$$$$$ 友朋支付调用 >>>>>>>>>>>>> 友朋支付调用 >>>>>>>>>>>>> 友朋支付调用 >>>>>>>>>>>>> 友朋支付调用");
		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>receipt_no=" + receipt_no);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>return_url=" + return_url);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>notify_url=" + notify_url);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>sign=" + sign);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>timestamp=" + timestamp);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>userAgent=" + userAgent);
		StringBuilder payUrl = new StringBuilder();
		try{
			YoPointApi ypApi = new YoPointApi();
			String respStr = ypApi.getConsumerOrderByReceiptNo(receipt_no);

			JSONObject order = JSONObject.parseObject(respStr);
			JSONObject data = order.getJSONObject("data");
			JSONObject product = data.getJSONObject("ProductVO");

			DecimalFormat df = new DecimalFormat("0.00");
			Integer payType = data.getInteger("PayType");//1：微信；2：支付宝；

//			5b4ee32bc0e8fe00120c8913 公司内大屏设备TID
			if(userAgent.indexOf("Alipay") >= 0 || userAgent.toLowerCase().indexOf("alipay") >= 0){
//				log.info("$$$$$$$$$$ 支付宝支付跳转 >>>>>>>>>>>>> 支付宝支付跳转 >>>>>>>>>>>>> 支付宝支付跳转>>>>>>>>>>>>>");
				String aliReturn_url = _p.getWdappURL() + "/pay/alipayinfo/"+receipt_no;
				String aliNotify_url = _p.getWdappURL() + "/pay/vendAliNotify";//异步回调支付结果通知地址
				String aliCancel_url = _p.getWdappURL() + "/pay/alipaycancel/"+receipt_no;

				String respRespStr = ypApi.getTerminalByTid(data.getString("TID"));
				JSONObject term = JSONObject.parseObject(respRespStr);
				JSONObject termData = term.getJSONObject("data");
				JSONObject terminal = termData.getJSONObject("TerminalConfigVO");
				JSONObject address2 = terminal.getJSONObject("PlaceVO");
				String realaddress= address2.getString("City") + address2.getString("District")+ terminal.getString("Address");
//				request.getSession().removeAttribute("wxreturn_url");
//				request.getSession().removeAttribute("wxnotify_url");
				AlipayResult<AlipayTradeWapPayResponse> alresult = getVendAliayWapResult(order, receipt_no, aliReturn_url, aliNotify_url);
				if(alresult.isSuccess()){
					request.setAttribute("form", alresult.getValue().getBody());
					request.setAttribute("title", data.getString("ProductName"));
					request.setAttribute("price", df.format(data.getDouble("Price")/100));
					request.setAttribute("imgurl", product.getString("ImageFixWidthUrl"));
					request.setAttribute("address", realaddress);
					request.setAttribute("receipt_no", data.getString("ReceiptNo"));
					cache.put("smsCache", "nfurl_"+receipt_no, notify_url);
					payUrl.append("pay/alipay");
				}else{
					payUrl.append("redirect:" + aliCancel_url);
				}
			}else if(userAgent.indexOf("MicroMessenger") >= 0 || userAgent.toLowerCase().indexOf("micromessenger") >= 0){
				NKAppConfig nkAppConfig = new NKAppConfig();
				WeixinTool wxTool = new WeixinTool(nkAppConfig);
				String wxAuthurl = wxTool.getWxAuthBaseUrl(_p.getWdappURL() + "/pay/vendWeixinPay", receipt_no);
				payUrl.append("redirect:").append(wxAuthurl);
				log.info("$$$$$$$$$$ 微信支付跳转 >>>>>>>>>>>>> 微信支付跳转 >>>>>>>>>>>>> 微信支付跳转 >>>>>>>>>>>>>");
//				request.getSession().setAttribute("wxreturn_url", return_url);
//				request.getSession().setAttribute("wxnotify_url", notify_url);
				cache.put("smsCache", "nfurl_"+receipt_no, notify_url);
			}
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		log.info("$$$$$$$$$$ getVendAliayUrl >>>>>>>>>>>>>aliay_url=" + payUrl);
		log.info("$$$$$$$$$$ 友朋支付调用 完成 >>>>>>>>>>>>> 完成  >>>>>>>>>>>>> 完成  >>>>>>>>>>>>> 完成");
		return payUrl.toString();
	}

	/**
	 * 微信JSAPI支付需要先获得openid，在微信浏览器中H5调起支付
	 * @param request
	 * @param response
	 * @param model
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "vendWeixinPay", method = RequestMethod.GET)
	public String vendWeixinPay( HttpServletRequest request,
								 HttpServletResponse response, Model model,
								 @RequestParam(required = true) String code,
								 @RequestParam(required = true) String state){

		String receipt_no = state;
		String return_url = _p.getWdappURL() + "/pay/wxwait?state="+receipt_no;//(String)request.getSession().getAttribute("wxreturn_url");
		String cancel_url = _p.getWdappURL() + "/pay/wxcancel";//(String)request.getSession().getAttribute("wxreturn_url");
		String notify_url = _p.getWdappURL() + "/pay/vendWeixinNotify";//异步回调支付结果通知地址
//		request.getSession().removeAttribute("wxreturn_url");
//		request.getSession().removeAttribute("wxnotify_url");

//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>return_url=" + return_url);
//		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>notify_url=" + notify_url);
		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>code=" + code);
		log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>state=" + state);
		try{
			JSONObject wxUserInfo = null;
			NKAppConfig nkAppConfig = new NKAppConfig();
			WeixinTool wxTool = new WeixinTool(nkAppConfig);
			wxUserInfo = wxTool.getWxUserBase(code);

			if(wxUserInfo != null){
				YoPointApi ypApi = new YoPointApi();
				String respStr = ypApi.getConsumerOrderByReceiptNo(receipt_no);
				JSONObject order = JSONObject.parseObject(respStr);
				JSONObject data = order.getJSONObject("data");
				JSONObject product = data.getJSONObject("ProductVO");
				String tid=data.getString("TID");
				String str = ypApi.getTerminalByTid(tid);
				JSONObject terminal = JSONObject.parseObject(str);
				JSONObject realter = terminal.getJSONObject("data");
				JSONObject address=realter.getJSONObject("TerminalConfigVO");
				JSONObject address2=address.getJSONObject("PlaceVO");
				String realaddress=address2.getString("City")+address2.getString("District")+address.getString("Address");
				request.setAttribute("imgurl", product.getString("ImageFixWidthUrl"));
				request.setAttribute("address",realaddress);
				Map<String, String> payData = getVendWeixinUnifiedOrder(wxUserInfo.getString("openid"), order, receipt_no, return_url, notify_url);
//				log.info("$$$$$$$$$$ appId >>>>>>>>>>>>>=" + payData.get("appId"));
//				log.info("$$$$$$$$$$ timeStamp >>>>>>>>>>>>>=" + payData.get("timeStamp"));
//				log.info("$$$$$$$$$$ nonceStr >>>>>>>>>>>>>=" + payData.get("nonceStr"));
//				log.info("$$$$$$$$$$ signType >>>>>>>>>>>>>=" + payData.get("signType"));
//				log.info("$$$$$$$$$$ package >>>>>>>>>>>>>=" + payData.get("package"));
//				log.info("$$$$$$$$$$ sign >>>>>>>>>>>>>=" + payData.get("sign"));
				request.setAttribute("paydata", payData);
				request.setAttribute("title", data.getString("ProductName"));
				DecimalFormat df = new DecimalFormat("0.00");
				request.setAttribute("price", df.format(data.getDouble("Price")/100));
				request.setAttribute("returnurl", return_url);
				request.setAttribute("cancelurl", cancel_url);
				request.setAttribute("receipt_no", receipt_no);

			}else{
//				log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>code=" + code);
//				log.info("$$$$$$$$$$ vendpay >>>>>>>>>>>>>state=" + state);
//				log.info("$$$$$$$$$$ wxUserInfo is null >>>>>>>>>>>>>");
			}
		}catch(Exception ex){
			log.info("$$$$$$$$$$ vendWeixinPay 友朋支付微信支付异常>>>>>>>>>>>>>code=" + code);
			log.error(ex.getMessage(), ex);
		}
		return "/pay/wxpay";
	}

	public Map<String, String> getVendWeixinUnifiedOrder(String openid, JSONObject order, String receiptNo, String returnUrl, String notifyUrl) throws Exception{
		JSONObject data = order.getJSONObject("data");
		String body = data.getString("ProductName");
		String out_trade_no = data.getString("ReceiptNo");
		BigDecimal price = data.getBigDecimal("Price");
		String total_fee = price.toString();
		String spbill_create_ip = "106.75.18.97";
//		String notify_url = "http://test.ddzhuan.cn/wdapp/pay/vendwxnotify";
		String trade_type = "JSAPI";//"MWEB";//"JSAPI";
//		WeixinPay.createUnifiedorderUrl(body, out_trade_no, total_fee, spbill_create_ip, notify_url, trade_type);
		MyConfig config = new MyConfig();
		WXPay wxpay = new WXPay(config);


		log.info("微信支付参数： body >>>>>>>>>>>>>=" + body);
		log.info("微信支付参数： out_trade_no >>>>>>>>>>>>>=" + out_trade_no);
		log.info("微信支付参数： total_fee >>>>>>>>>>>>>=" + total_fee);

		Map<String, String> wxdata = new HashMap<String, String>();
		wxdata.put("body", body);
		wxdata.put("out_trade_no", out_trade_no);
		wxdata.put("device_info", "");
		wxdata.put("fee_type", "CNY");
		wxdata.put("total_fee", total_fee);
		wxdata.put("spbill_create_ip", spbill_create_ip);
		wxdata.put("notify_url", notifyUrl);
		wxdata.put("trade_type", trade_type); // 此处指定为扫码支付
		if(openid != null && !"".equals(openid)){
			wxdata.put("openid", openid); // JSAPI支付必须传
		}
		// data.put("product_id", "12");

		Map<String, String> resp = wxpay.unifiedOrder(wxdata);

//		log.info("微信统计下单返回： appid >>>>>>>>>>>>>=" + resp.get("appid"));
//		log.info("微信统计下单返回： mch_id >>>>>>>>>>>>>=" + resp.get("mch_id"));
//		log.info("微信统计下单返回： device_info >>>>>>>>>>>>>=" + resp.get("device_info"));
//		log.info("微信统计下单返回： nonce_str >>>>>>>>>>>>>=" + resp.get("nonce_str"));
//		log.info("微信统计下单返回： sign >>>>>>>>>>>>>=" + resp.get("sign"));
//
//		log.info("微信统计下单返回： result_code >>>>>>>>>>>>>=" + resp.get("result_code"));
//		log.info("微信统计下单返回： return_code >>>>>>>>>>>>>=" + resp.get("return_code"));
//		log.info("微信统计下单返回： return_msg >>>>>>>>>>>>>=" + resp.get("return_msg"));
//		log.info("微信统计下单返回： err_code >>>>>>>>>>>>>=" + resp.get("err_code"));
//		log.info("微信统计下单返回： err_code_des >>>>>>>>>>>>>=" + resp.get("err_code_des"));
//
//		log.info("微信统计下单返回： trade_type >>>>>>>>>>>>>=" + resp.get("trade_type"));
//		log.info("微信统计下单返回： prepay_id >>>>>>>>>>>>>=" + resp.get("prepay_id"));
//		log.info("微信统计下单返回： code_url >>>>>>>>>>>>>=" + resp.get("code_url"));


		Map<String, String> payData = new HashMap<String, String>();
		payData.put("appId", config.getAppID());
		payData.put("timeStamp", System.currentTimeMillis()/1000+"");
		payData.put("nonceStr", WXPayUtil.generateNonceStr());
		payData.put("signType", WXPayConstants.getSignTypeStr(wxpay.getSignType()));
		payData.put("package", "prepay_id="+resp.get("prepay_id"));
		payData.put("sign", WXPayUtil.generateSignature(payData, config.getKey(), wxpay.getSignType()));//WXPayConstants.SignType.HMACSHA256
		return payData;
	}

	public AlipayResult<AlipayTradeWapPayResponse> getVendAliayWapResult(JSONObject order, String receiptNo, String returnUrl, String notifyUrl){
		JSONObject data = order.getJSONObject("data");
		AlipayResult<AlipayTradeWapPayResponse> result = new AlipayResult<AlipayTradeWapPayResponse>();
		try{
			//支付接口
			String gateway = "https://openapi.alipay.com/gateway.do";

			//初始化请求类
			AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
			alipayRequest.setReturnUrl(returnUrl);
			alipayRequest.setNotifyUrl(notifyUrl);
			JSONObject bizJson = new JSONObject();
			DecimalFormat df = new DecimalFormat("0.00");
			String cancel_url = _p.getWdappURL() + "/pay/alipaycancel/"+data.getString("ReceiptNo");
			AlipayTradeWapPayModel alipayModel = new AlipayTradeWapPayModel();
			alipayModel.setSubject(data.getString("ProductName"));//String	必选	256	商品的标题/交易标题/订单标题/订单关键字等
			alipayModel.setOutTradeNo(data.getString("ReceiptNo"));//String	必选	64	商户网站唯一订单号
			alipayModel.setTotalAmount(df.format(data.getDouble("Price")/100));//Price	必选	9	订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
			alipayModel.setQuitUrl(cancel_url);//String	必选	400	用户付款中途退出返回商户网站的地址
			alipayModel.setProductCode(data.getString("id"));//String	必选	64	销售产品码，商家和支付宝签约的产品码
			alipayRequest.setBizModel(alipayModel);
			AlipayClient alipayClient = new DefaultAlipayClient(gateway, AlipayConfigEnum.DDZ_ALI_OPEN_APP_ID.getValue(),
					AlipayConfigEnum.DDZ_ALI_OPEN_PRIVATE_KEY.getValue(), "json", "UTF-8",
					AlipayConfigEnum.DDZ_ALI_OPEN_PUBLIC_KEY.getValue(), "RSA2");
			AlipayTradeWapPayResponse alipayResponse = alipayClient.pageExecute(alipayRequest);
			String form = alipayResponse.getBody();
			result.setSuccess(true);
			result.setValue(alipayResponse);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			if(e.getCause() instanceof java.security.spec.InvalidKeySpecException){
				result.setMessage("商户私钥格式不正确，请确认配置文件Alipay-Config.properties中是否配置正确");
				return result;
			}
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			ex.getMessage();
		}
		return result;
	}

	/**
	 * 友朋售货机阿里微信成功页
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "vendWeixinNotify")
	public void vendWeixinNotifyUrl(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		log.info("$$$$$$$$$$ 微信支付NOTIFY >>>>>>>>>>>>> 微信支付NOTIFY >>>>>>>>>>>>> 微信支付NOTIFY >>>>>>>>>>>>> 微信支付NOTIFY");
		try{
			BufferedReader reader = request.getReader();
			StringBuffer buff = new StringBuffer();
			if(reader != null){
				String str = "";
				while ((str = reader.readLine()) != null) {
					buff.append(str);
				}
			}
			Map<String, String> map = WXPayUtil.xmlToMap(buff.toString());
			String receiptNo = map.get("out_trade_no");
			String tradeNo = map.get("transaction_id");
			String price = map.get("cash_fee");
			boolean tradeSuccess = "SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"));
			String ypnofUrl = (String)cache.get("smsCache", "nfurl_"+receiptNo);
			String ypResult = "";

			log.error("$$$$$$$$$$ 出货中NOTIFY >>>>>>>>>>>>> buff=" + buff.toString());
			log.error("$$$$$$$$$$ 出货中NOTIFY >>>>>>>>>>>>> ypnofUrl=" + ypnofUrl);
			log.error("$$$$$$$$$$ 出货中NOTIFY >>>>>>>>>>>>> return_code=" + map.get("return_code") + " result_code=" + map.get("result_code"));

			YoPointApi ypa = new YoPointApi();
			if(tradeSuccess){
				ypResult = ypa.payNotify(ypnofUrl, receiptNo, tradeNo,price, 1);
				log.error("$$$$$$$$$$ 出货中NOTIFY >>>>>>>>>>>>> receiptNo=" + receiptNo + " tradeNo=" + tradeNo+" ypResult="+ypResult);
				if("success".equals(ypResult)){
					YouPengOrder youPengOrder = yPOrderService.queryOrderByYPreceiptnoAndTradeno(receiptNo, tradeNo);
					if (youPengOrder == null) {
						YouPengOrder ypOrder=new YouPengOrder();
						ypOrder.setPaytype(1);
						ypOrder.setOrderstatus(0);
						ypOrder.setTradeno(tradeNo);
						ypOrder.setYpreceiptno(receiptNo);
						ypOrder.setOutstatus(0);
						yPOrderService.addOrder(ypOrder);
					}
					log.error("$$$$$$$$$$ 出货成功NOTIFY >>>>>>>>>>>>> receiptNo=" + receiptNo + " tradeNo=" + tradeNo+" ypResult="+ypResult);
				}else{
					log.error("$$$$$$$$$$ 出货失败NOTIFY >>>>>>>>>>>>> receiptNo=" + receiptNo + " tradeNo=" + tradeNo+" ypResult="+ypResult);
				}
			}else{
				ypResult = ypa.payNotify(ypnofUrl, receiptNo, tradeNo, price, -1);
			}
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * 微信完成支付，等待出货页
	 * @param request
	 * @param response
	 * @param model
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "wxwait")
	public String vendWeixinPayWait( HttpServletRequest request,
									 HttpServletResponse response, Model model,
									 @RequestParam(required = true) String state){
		log.info("友朋支付API WAIT >>>>>>>>> 友朋支付API WAIT >>>>>>>>> 友朋支付API WAIT ");
		String receipt_no = state;
		YoPointApi ypApi = new YoPointApi();
		String respStr = ypApi.getConsumerOrderByReceiptNo(receipt_no);
		JSONObject order = JSONObject.parseObject(respStr);
		JSONObject data = order.getJSONObject("data");
		JSONObject product = data.getJSONObject("ProductVO");
		String tid=data.getString("TID");
		String str = ypApi.getTerminalByTid(tid);
		JSONObject terminal = JSONObject.parseObject(str);
		JSONObject realter = terminal.getJSONObject("data");
		JSONObject address=realter.getJSONObject("TerminalConfigVO");
		JSONObject address2=address.getJSONObject("PlaceVO");
		String realaddress=address2.getString("City")+address2.getString("District")+address.getString("Address");
		request.setAttribute("title", data.getString("ProductName"));
		DecimalFormat df = new DecimalFormat("0.00");
		request.setAttribute("price", df.format(data.getDouble("Price")/100));
		request.setAttribute("imgurl", product.getString("ImageFixWidthUrl"));
		request.setAttribute("address",realaddress);
		return "/pay/wait";
	}

	/**
	 * 微信取消支付页
	 * @param request
	 * @param response
	 * @param model
	 * @param receipt_no
	 * @return
	 */
	@RequestMapping(value = "wxcancel", method = RequestMethod.GET)
	public String vendWeixinPayCancel( HttpServletRequest request,
									   HttpServletResponse response, Model model,@RequestParam(required = true) String receipt_no ){
		try{
			YoPointApi ypApi = new YoPointApi();
			String respStr = ypApi.getConsumerOrderByReceiptNo(receipt_no);
			JSONObject order = JSONObject.parseObject(respStr);
			JSONObject data = order.getJSONObject("data");
			JSONObject product = data.getJSONObject("ProductVO");
			String tid=data.getString("TID");
			String str = ypApi.getTerminalByTid(tid);
			JSONObject terminal = JSONObject.parseObject(str);
			JSONObject realter = terminal.getJSONObject("data");
			JSONObject address=realter.getJSONObject("TerminalConfigVO");
			JSONObject address2=address.getJSONObject("PlaceVO");
			String realaddress=address2.getString("City")+address2.getString("District")+address.getString("Address");
			request.setAttribute("title", data.getString("ProductName"));
			DecimalFormat df = new DecimalFormat("0.00");
			request.setAttribute("price", df.format(data.getDouble("Price")/100));
			request.setAttribute("imgurl", product.getString("ImageFixWidthUrl"));
			request.setAttribute("address",realaddress);
			request.setAttribute("receipt_no",receipt_no);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return "/pay/cancel";
	}

	/**
	 * 友朋售货机阿里支付回调页
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "vendAliNotify")
	public void vendAliNotifyUrl(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		log.info("$$$$$$$$$$ ALI支付NOTIFY >>>>>>>>>>>>> ALI支付NOTIFY >>>>>>>>>>>>> ALI支付NOTIFY >>>>>>>>>>>>> ALI支付NOTIFY");
		try{
			DecimalFormat df = new DecimalFormat("0.00");
			String aliAppid = request.getParameter("app_id");
			String receiptNo = request.getParameter("out_trade_no");
			String tradeNo = request.getParameter("trade_no");
			String price = request.getParameter("total_amount");
//			log.info("ALI支付下单返回： aliAppid >>>>>>>>>>>>>=" + aliAppid);
//			log.info("ALI支付下单返回： receiptNo >>>>>>>>>>>>>=" + receiptNo);
//			log.info("ALI支付下单返回： tradeNo >>>>>>>>>>>>>=" + tradeNo);
//			log.info("ALI支付下单返回： price >>>>>>>>>>>>>=" + price);
			Double dPrice = Double.parseDouble(price) * 100;
			price = dPrice.intValue()+"";
			String tradeStatus = request.getParameter("trade_status");
			String ypResult = "";
			boolean tradeSuccess = "TRADE_SUCCESS".equals(tradeStatus);;
			String ypnofUrl = (String)cache.get("smsCache", "nfurl_" + receiptNo);
			YoPointApi ypa = new YoPointApi();

			if(tradeSuccess){
				ypResult = ypa.payNotify(ypnofUrl, receiptNo, tradeNo,price, 1);
				log.error("$$$$$$$$$$ 出货中NOTIFY >>>>>>>>>>>>> receiptNo=" + receiptNo + " tradeNo=" + tradeNo+" ypResult="+ypResult);
				if("success".equals(ypResult)){
					YouPengOrder youPengOrder = yPOrderService.queryOrderByYPreceiptnoAndTradeno(receiptNo, tradeNo);
					if (youPengOrder == null) {
						YouPengOrder ypOrder = new YouPengOrder();
						ypOrder.setPaytype(0);
						ypOrder.setOrderstatus(0);
						ypOrder.setTradeno(tradeNo);
						ypOrder.setYpreceiptno(receiptNo);
						ypOrder.setOutstatus(0);
						yPOrderService.addOrder(ypOrder);
					}
					log.error("$$$$$$$$$$ 出货成功NOTIFY >>>>>>>>>>>>> receiptNo=" + receiptNo + " tradeNo=" + tradeNo+" ypResult="+ypResult);
				}else{
					log.error("$$$$$$$$$$ 出货失败NOTIFY >>>>>>>>>>>>> receiptNo=" + receiptNo + " tradeNo=" + tradeNo+" ypResult="+ypResult);
				}
			}else{
				ypResult = ypa.payNotify(ypnofUrl, receiptNo, tradeNo, price, -1);
			}
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * 友朋售货机阿里支付成功页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/alipayinfo/{repno}", method = RequestMethod.GET)
	public String getAliPayReturn(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("$$$$$$$$$$ 友朋ALI支付详情 >>>>>>>>>>>>> receiptNo >>>>>>>>>>>>> receiptNo >>>>>>>>>>>>> receiptNo");
		String[] uri = request.getRequestURI().split("/");
		try {
			String receiptNo = uri[uri.length - 1];
			log.info("$$$$$$$$$$ 友朋ALI支付详情 >>>>>>>>>>>>> receiptNo=" + receiptNo);
			YoPointApi ypApi = new YoPointApi();
			String respStr = ypApi.getConsumerOrderByReceiptNo(receiptNo);
			JSONObject order = JSONObject.parseObject(respStr);
			JSONObject data = order.getJSONObject("data");
			JSONObject product = data.getJSONObject("ProductVO");
			String tid=data.getString("TID");
			String str = ypApi.getTerminalByTid(tid);
			JSONObject terminal = JSONObject.parseObject(str);
			JSONObject realter = terminal.getJSONObject("data");
			JSONObject address=realter.getJSONObject("TerminalConfigVO");
			JSONObject address2=address.getJSONObject("PlaceVO");
			String realaddress=address2.getString("City")+address2.getString("District")+address.getString("Address");
			request.setAttribute("title", data.getString("ProductName"));
			DecimalFormat df = new DecimalFormat("0.00");
			request.setAttribute("price", df.format(data.getDouble("Price")/100));
			request.setAttribute("imgurl", product.getString("ImageFixWidthUrl"));
			request.setAttribute("address",realaddress);
			request.setAttribute("receipt_no",receiptNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pay/wait";
	}

	/**
	 * 友朋售货机阿里支付取消、失败页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/alipaycancel/{repno}", method = RequestMethod.GET)
	public String getAliPayCancel(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("$$$$$$$$$$ 友朋ALI支付取消详情 >>>>>>>>>>>>> receiptNo >>>>>>>>>>>>> receiptNo >>>>>>>>>>>>> receiptNo");
		String[] uri = request.getRequestURI().split("/");
		try {
			String receiptNo = uri[uri.length - 1];
			log.info("$$$$$$$$$$ 友朋ALI支付取消详情 >>>>>>>>>>>>> receiptNo=" + receiptNo);
			DecimalFormat df = new DecimalFormat("0.00");
			YoPointApi ypApi = new YoPointApi();
			String respStr = ypApi.getConsumerOrderByReceiptNo(receiptNo);
			JSONObject order = JSONObject.parseObject(respStr);
			JSONObject data = order.getJSONObject("data");
			JSONObject product = data.getJSONObject("ProductVO");
			String str = ypApi.getTerminalByTid(data.getString("TID"));
			JSONObject terminal = JSONObject.parseObject(str);
			JSONObject realter = terminal.getJSONObject("data");
			JSONObject address = realter.getJSONObject("TerminalConfigVO");
			JSONObject address2 = address.getJSONObject("PlaceVO");
			String realaddress = address2.getString("City")+address2.getString("District")+address.getString("Address");


			request.setAttribute("title", data.getString("ProductName"));
			request.setAttribute("price", df.format(data.getDouble("Price")/100));
			request.setAttribute("imgurl", product.getString("ImageFixWidthUrl"));
			request.setAttribute("address",realaddress);
			request.setAttribute("receipt_no",receiptNo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "pay/cancel";
	}


	public Map<String, String> aliPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("$$$$$$$$$$ 支付NOTIFY >>>>>>>>>>>>> 支付NOTIFY >>>>>>>>>>>>> 支付NOTIFY >>>>>>>>>>>>> 支付NOTIFY");
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		PrintWriter out = response.getWriter();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			// System.out.println(valueStr);
			params.put(name, valueStr);
		}



		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		// 商户订单号
		String out_trade_no = request.getParameter("out_trade_no");

		// 支付宝交易号
		String trade_no = request.getParameter("trade_no");

		// 交易状态
		String trade_status = request.getParameter("trade_status");

		log.info("$$$$$$$$$$ vendNotify >>>>>>>>>>>>>out_trade_no=" + out_trade_no);
		log.info("$$$$$$$$$$ vendNotify >>>>>>>>>>>>>trade_no=" + trade_no);
		log.info("$$$$$$$$$$ vendNotify >>>>>>>>>>>>>trade_status=" + trade_status);

		String respStr = "fail";
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		YoPointApi ypApi = new YoPointApi();
		if (AlipayNotify.verify(params)) {// 验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序
//				String respStr = ypApi.payNotify(out_trade_no, trade_no, -1);
				System.out.println("异步TRADE_FINISHED：订单号 > " + out_trade_no + "交易号：" + trade_no);
				// 注意：
				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序
				String ypnofUrl = (String)cache.get("smsCache", "nfurl_"+out_trade_no);
				respStr = ypApi.payNotify(ypnofUrl, out_trade_no, trade_no, "",1);
				System.out.println("异步TRADE_SUCCESS：订单号 > " + out_trade_no);
				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
			}

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			System.out.println("success");
			out.println("success"); // 请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			try {
				StringBuffer error = new StringBuffer();
				error.append("异步订单号:" + out_trade_no);
				error.append("异步交易号:" + trade_no);
				throw new Exception(error.toString());
			} catch (Exception e) {
				JSONObject json = new JSONObject();
				json.putAll(requestParams);
				log.error(e.getMessage() + " > " + json.toString());
			} finally {
				String ypnofUrl = (String)cache.get("smsCache", "nfurl_"+out_trade_no);
				respStr = ypApi.payNotify(ypnofUrl,out_trade_no, trade_no,"", -1);
				System.out.println("fail");
				out.println("fail");
			}
		}
		log.info("$$$$$$$$$$ 支付NOTIFY完成 >>>>>>>>>>>>> 完成 >>>>>>>>>>>>> 完成 >>>>>>>>>>>>> 完成");
		return null;
	}

	public String getVendAliayUrl(JSONObject order, String receiptNo, String returnUrl, String notifyUrl){
		JSONObject data = order.getJSONObject("data");
		try{
			//支付接口
			String gateway = "https://mapi.alipay.com/gateway.do?";
			//服务名称，这个是识别是何接口实现何功能的标识，请勿修改
			String Service = "alipay.wap.create.direct.pay.by.user";
//			String Service = "alipay.trade.wap.pay";
			BigDecimal price = data.getBigDecimal("Price");//order.getDouble("Price");//实付金额BigDecimal dec = new BigDecimal("0.00");
			// 客户自己的订单号，订单号必须在自身订单系统中保持唯一性
			String out_trade_no = data.getString("ReceiptNo");
			String total_fee = price.toString();
			String subject = data.getString("ProductName");
			String body = data.getString("ProductName");
			String return_url = returnUrl;
			String notify_url = returnUrl;

			Long createTimestamp = data.getLong("CreateAt") * 1000;
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(createTimestamp);
//			cal.add(Calendar.MINUTE, _p.getPaymentTime());
			cal.add(Calendar.MINUTE, paymentTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			sdf.format(cal.getTime());
			// 超时时间
			String it_b_pay = sdf.format(cal.getTime());

			String show_url = _p.getWdappURL()+"/pay/paydinfo/" + receiptNo;

			String[] para = { "service=" + Service, "partner=" + AlipayConfig.partner, "it_b_pay=" + it_b_pay,
					"seller_id=" + AlipayConfig.seller_id, "out_trade_no=" + out_trade_no, "subject=" + subject,
					"body=" + body, "total_fee=" + total_fee, "payment_type=1", "notify_url=" + notify_url,
					"show_url=" + show_url, "return_url=" + return_url, "_input_charset=" + AlipayConfig.input_charset };

			// 支付URL生成
			String aliay_url = AliPay.CreatUrl(gateway, // get方式传递参数时请去掉注释
					para, AlipayConfig.input_charset, AlipayConfig.sign_type, AlipayConfig.private_key);

			return aliay_url;
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return "redirect:http://www.ddzhuan.cn";
	}

	/**
	 * 友朋回调详情页面
	 */
	@RequestMapping(value = "toorder", method = RequestMethod.GET)
	public String toorder(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return "/pay/cancel";
	}

//	@RequestMapping(value = "finish", method = RequestMethod.GET)
//	public String youPengCallback(HttpServletRequest request, HttpServletResponse response, Model model,
//			@RequestParam(required = true) String method,
//			@RequestParam(required = true) String biz_content,
//			@RequestParam(required = true) Integer timestamp,
//			@RequestParam(required = true) String sign_type,
//			@RequestParam(required = true) String sign) throws Exception{
//		StringBuilder testPayUrl = new StringBuilder();
//		if(!"http://test.ddzhuan.cn/wdapp".equals(_p.getWdappURL())){
//			testPayUrl.append("redirect:http://test.ddzhuan.cn/wdapp/pay/finish?method=")
//			.append(java.net.URLEncoder.encode(method,"UTF-8"))
//			.append("&biz_content=").append(java.net.URLEncoder.encode(biz_content,"UTF-8"))
//			.append("&sign_type=").append(sign_type)
//			.append("&sign=").append(sign)
//			.append("&timestamp=").append(timestamp);
//			log.info("testPay >>>>>>>>>>>" + testPayUrl);
//		}
//		return testPayUrl.toString();
//	}

	/**
	 * 友朋退款入口
	 * @param request
	 * @param response
	 * @param model
	 * @param method
	 * @param biz_content
	 * @param timestamp
	 * @param sign_type
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "finish", method = RequestMethod.POST)
	@ResponseBody
	public YouPengResultInfo youPengCallback(HttpServletRequest request, HttpServletResponse response, Model model,
											 @RequestParam(required = true) String method,
											 @RequestParam(required = true) String biz_content,
											 @RequestParam(required = true) long timestamp,
											 @RequestParam(required = true) String sign_type,
											 @RequestParam(required = true) String sign) {
//		log.info("-----------------------------ceshi" + method);
//		log.info("-----------------------------ceshi" + biz_content);
//		log.info("-----------------------------ceshi" + timestamp);
//		log.info("-----------------------------ceshi" + sign_type);
//		log.info("-----------------------------ceshi" + sign);

//		try{
//			JSONObject json = JSONObject.parseObject(biz_content);
//			String receiptNo = json.getString("ReceiptNo");
//			YoPointApi ypApi = new YoPointApi();
//			String respStr = ypApi.getConsumerOrderByReceiptNo(receiptNo);
//			JSONObject order = JSONObject.parseObject(respStr);
//			JSONObject data = order.getJSONObject("data");
//			String tid = data.getString("TID");
//
//
//			StringBuilder testPayUrl = new StringBuilder();
//			if("5b4ee32bc0e8fe00120c8913".equals(tid) && !"http://test.ddzhuan.cn/wdapp".equals(_p.getWdappURL())){
//				testPayUrl.append("redirect:http://test.ddzhuan.cn/wdapp/pay/finish?method=")
//				.append(java.net.URLEncoder.encode(method,"UTF-8"))
//				.append("&biz_content=").append(java.net.URLEncoder.encode(biz_content,"UTF-8"))
//				.append("&sign_type=").append(sign_type)
//				.append("&sign=").append(sign)
//				.append("&timestamp=").append(timestamp);
//				log.info("jompto test finish >>>>>>>>>>>" + testPayUrl);
//				response.sendRedirect(testPayUrl.toString());
//				return null;
//			}
//		}catch(Exception ex){
//			log.error("退款跳转测试环境异常......");
//			log.error(ex.getMessage(), ex);
//		}


		YouPengResultInfo youPeng = new YouPengResultInfo();
		youPeng.setError_code(-1);
		youPeng.setError_msg(null);
		if ("api.test".equals(method)) {
			youPeng.setRefundsRawData(biz_content);
			youPeng.setError_code(0);
			youPeng.setError_msg(null);
		} else if ("consumer.order.refunds".equals(method)) {
			log.info("退款执行的method=========================================>"+method);
			// 执行友朋主动退款
			JSONObject json = JSONObject.parseObject(biz_content);
			String receiptNo = json.getString("ReceiptNo");
			String tradeNo = json.getString("TradeNo");
			if (receiptNo != null && tradeNo != null) {
				YoPointApi ypApi = new YoPointApi();
//				log.info("-----------------receiptNo" + receiptNo);
//				log.info("-----------------tradeNo" + tradeNo);
				String data = ypApi.getConsumerOrderByReceiptNo(receiptNo);
				JSONObject yporder = JSONObject.parseObject(data);
				YouPengOrder order = yPOrderService.queryOrderByYPreceiptnoAndTradeno(receiptNo, tradeNo);
				if (order != null && order.getPaytype() == 0) {
					// 调支付宝退款
					AlipayResult<AlipayTradeRefundResponse> aliRefundResult = getAliRefundResult(receiptNo);
					if (aliRefundResult.isSuccess() && aliRefundResult.getValue().isSuccess()) {
						// 退款成功
						youPeng.setError_code(0);
						youPeng.setError_msg("SUCCESS");
						youPeng.setRefundsRawData(aliRefundResult.getValue());
						youPeng.setRefundsTime(yporder.getString("RefundsTime"));
						order.setOrderstatus(2);
						yPOrderService.renewOrder(order);
					} else {
						// 退款失败
						youPeng.setError_code(-1);
						youPeng.setError_msg(aliRefundResult.getValue().getSubMsg());
						order.setOrderstatus(3);
						yPOrderService.renewOrder(order);
					}
				} else if (order != null && order.getPaytype() == 1) {
					// 调微信退款
					try {
						Map<String, String> mapResult = getRefundWeixin(receiptNo);
						log.info("微信退款结果：");
						log.info("---------return_code" + mapResult.get("return_code"));
						log.info("---------return_msg" + mapResult.get("return_msg"));
						log.info("---------result_code" + mapResult.get("result_code"));
						log.info("---------err_code" + mapResult.get("err_code"));
						log.info("---------err_code_des" + mapResult.get("err_code_des"));
						if ("SUCCESS".equals(mapResult.get("return_code"))
								&& "SUCCESS".equals(mapResult.get("result_code"))) {
							youPeng.setError_code(0);
							youPeng.setError_msg("SUCCESS");
							youPeng.setRefundsRawData(mapResult);
							youPeng.setRefundsTime(yporder.getString("RefundsTime"));
							order.setOrderstatus(2);
							int uc = yPOrderService.renewOrder(order);
							log.info("微信成功：" + uc);
							if(uc != 1){
								log.info("---------Tradeno" + order.getTradeno());
								log.info("---------Ypreceiptno" + order.getYpreceiptno());
								log.info("---------Orderstatus" + order.getOrderstatus());
							}

						} else {
							// 微信退款失败
							youPeng.setError_code(-1);
							youPeng.setError_msg(mapResult.get("return_msg"));
							order.setOrderstatus(3);
							int uc = yPOrderService.renewOrder(order);
							log.info("微信失败："+uc);
							if(uc != 1){
								log.info("---------Tradeno" + order.getTradeno());
								log.info("---------Ypreceiptno" + order.getYpreceiptno());
								log.info("---------Orderstatus" + order.getOrderstatus());
							}
						}
					} catch (Exception ex) {
						log.info("微信退款失败");
						log.error(ex.getMessage(), ex);
					}
				}
			}
		} else if ("consumer.order.refunds.notify".equals(method)) {
			log.info("退款执行的method=========================================>"+method);
			JSONObject json = JSONObject.parseObject(biz_content);
			youPeng.setError_code(0);
			youPeng.setError_msg("SUCCESS");

		}
		return youPeng;
	}

	/**
	 * 支付宝退款
	 *
	 * @param receiptNo
	 * @return
	 */
	public AlipayResult<AlipayTradeRefundResponse> getAliRefundResult(String receiptNo) {
		AlipayResult<AlipayTradeRefundResponse> result = new AlipayResult<AlipayTradeRefundResponse>();
		YoPointApi ypApi = new YoPointApi();
		String data = ypApi.getConsumerOrderByReceiptNo(receiptNo);
		JSONObject data2 = JSONObject.parseObject(data);
		JSONObject order = JSONObject.parseObject(data2.getString("data"));
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			// 支付接口
			String gateway = "https://openapi.alipay.com/gateway.do";
			String reason = order.getString("UserRefundsReason");
			if ("REFUNDS_REASON_DELIVERY_FAIL".equals(reason)) {
				reason = "没有掉货";
			} else if ("REFUNDS_REASON_PRODUCT_NOT_MATCH".equals(reason)) {
				reason = "掉错商品";
			} else if ("REFUNDS_REASON_PRODUCT_BROKEN".equals(reason)) {
				reason = "商品损坏";
			} else if ("REFUNDS_REASON_PRODUCT_CANNOT_PICKOUT".equals(reason)) {
				reason = "商品无法取出";
			}
			AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();
			AlipayTradeRefundModel model = new AlipayTradeRefundModel();
			model.setOutTradeNo(order.getString("ReceiptNo"));
			model.setRefundAmount(df.format(order.getDouble("Price") / 100));
			model.setRefundReason(reason);
			alipay_request.setBizModel(model);
			AlipayClient client = new DefaultAlipayClient(gateway, AlipayConfigEnum.DDZ_ALI_OPEN_APP_ID.getValue(),
					AlipayConfigEnum.DDZ_ALI_OPEN_PRIVATE_KEY.getValue(), "json", "UTF-8",
					AlipayConfigEnum.DDZ_ALI_OPEN_PUBLIC_KEY.getValue(), "RSA2");
			AlipayTradeRefundResponse alipay_response = client.execute(alipay_request);
			String form = alipay_response.getBody();
			result.setSuccess(true);
			result.setValue(alipay_response);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			if (e.getCause() instanceof java.security.spec.InvalidKeySpecException) {
				result.setMessage("商户私钥格式不正确，请确认配置文件Alipay-Config.properties中是否配置正确");
				return result;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			ex.getMessage();
		}
		return result;
	}

	/**
	 * 微信退款
	 *
	 * @param receiptNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getRefundWeixin(String receiptNo) {
		YoPointApi ypApi = new YoPointApi();
		Map<String, String> resp = null;
		String data = ypApi.getConsumerOrderByReceiptNo(receiptNo);
		JSONObject data2 = JSONObject.parseObject(data);
		JSONObject order = JSONObject.parseObject(data2.getString("data"));
		String total_fee = order.getString("OriginalPrice");
		String refund_fee = order.getString("Price");
		String out_refund_no = order.getString("ReceiptNo");
		String out_trade_no = order.getString("ReceiptNo");
//		log.info("-----------refund_fee" + refund_fee);
//		log.info("-----------total_fee" + total_fee);
//		log.info("-----------out_refund_no" + out_refund_no);
//		log.info("-----------out_trade_no" + out_trade_no);
		try {
			MyConfig config = new MyConfig();
			WXPay wxpay = new WXPay(config);
			HashMap<String, String> wxdata = new HashMap<String, String>();
			wxdata.put("total_fee", total_fee);
			wxdata.put("refund_fee", refund_fee);
			wxdata.put("out_refund_no", out_refund_no);
			wxdata.put("out_trade_no", out_trade_no);
			resp = wxpay.refund(wxdata);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.getMessage();
		}
		return resp;
	}

//	// 测试用，更新退款状态
//	@RequestMapping(value = "updateRefund", method = RequestMethod.POST)
//	@ResponseBody
//	public String youPengUpdateRefund(HttpServletRequest request, HttpServletResponse response, Model model,
//			@RequestParam(required = true) String receiptNo, @RequestParam(required = true) int userRefundsStatus) {
//		YoPointApi ypApi = new YoPointApi();
//		String data = ypApi.updateRefundStatus(receiptNo, userRefundsStatus);
//		return data;
//
//	}
//
//	// 测试用，拿到友朋订单信息
//	@RequestMapping(value = "getinfo", method = RequestMethod.POST)
//	@ResponseBody
//	public String getinfo(HttpServletRequest request, HttpServletResponse response, Model model,
//			@RequestParam(required = true) String receiptNo) {
//		YoPointApi ypApi = new YoPointApi();
//		String data = ypApi.getConsumerOrderByReceiptNo(receiptNo);
//		return data;
//	}

//	public String getDDZAliayUrl(long uid, long id, long paylist,
//			HttpServletRequest request,
//			HttpServletResponse response, Model model) throws Exception{
//		String gateway = "https://mapi.alipay.com/gateway.do?"; // '支付接口
//
//		// string Service = "create_direct_pay_by_user";
//		// //服务名称，这个是识别是何接口实现何功能的标识，请勿修改
//		String Service = "alipay.wap.create.direct.pay.by.user"; // 服务名称，这个是识别是何接口实现何功能的标识，请勿修改
//
//		// String seller_id = "suncp@nankang.com.cn"; // 商家签约时的支付宝帐号，即收款的支付宝帐号
//		// String sign_type = "MD5"; // 加密类型,签名方式“不用改”
//		// String key = "noy279q29ifz9ibilxc0ixc9aas646fu"; //
//		// 安全校验码，与partner是一组，获取方式是：用签约时支付宝帐号登陆支付宝网站www.alipay.com，在商家服务我的商家里即可查到。
//		// String partner = "2088601292047791"; // 商户ID，合作身份者ID，合作伙伴ID
//		// String _input_charset = "utf-8"; //
//		// 编码类型，完全根据客户自身的项目的编码格式而定，千万不要填错。否则极其容易造成MD5加密错误。
//		String url = _p.getWdappURL();
//
//		String show_url = url + "/aliPay/" + uid; //
//		// 展示地址，即在支付页面时，商品名称旁边的“详情”的链接地址。
//
//		String out_trade_no = order.getOrderNumber();// orderNumber; //
//
//		String subject = "商品名称";// goodsInfo.GoodsName; //
//								// 商品名称，也可称为订单名称，该接口并不是单一的只能买一样东西，可把一次支付当作一次下订单
//		String body = "商品描述";// goodsInfo.Memo; // 商品描述，即备注
////		if (order.getOrderDetailList() != null)
////			for (OrderDetail detail : order.getOrderDetailList()) {
////				GoodsItem goods = goodsItemService.getGoodsByGoodsId(detail.getGoodsId());
////				if (StringUtils.isNotEmpty(goods.getGoodsName())) {
////					subject = goods.getGoodsName();
////				}
////				// if (StringUtils.isNotEmpty(goods.getMemo())) {
////				// body = goods.getMemo();
////				// }
////			}
//		// subject = java.net.URLEncoder.encode(subject, "utf-8");
//		// body = java.net.URLEncoder.encode(body, "utf-8");
////		String total_fee = "0.01"; // 商品价格，也可称为订单的总金额
//		String total_fee = order.getAliPayTotalPrice().toString();
//
//		// 计算超时付款时间
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(order.getCreateTime());
//		cal.add(Calendar.MINUTE, _p.getPaymentTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		sdf.format(cal.getTime());
//		// 超时时间
//		String it_b_pay = sdf.format(cal.getTime());
//		// System.out.println(it_b_pay);
//
//		// 服务器通知url（Alipay_Notify.aspx文件所在路经），必须是完整的路径地址
//		// String notify_url =
//		// "http://nkwltestmain.chinacloudapp.cn:8080/APPMarket/pay/notifyUrl";
//		String notify_url = url + "/pay/notifyUrl";
//		// 服务器返回url（Alipay_Return.aspx文件所在路经），必须是完整的路径地址
//		// String return_url =
//		// "http://nkwltestmain.chinacloudapp.cn:8080/APPMarket/pay/returnUrl";
//		String return_url = url + "/pay/returnUrl";
//
//		// 构造数组；
//		// 以下数组即是参与加密的参数，若参数的值不允许为空，若该参数为空，则不要成为该数组的元素
//		String[] para = { "service=" + Service, "partner=" + AlipayConfig.partner, "it_b_pay=" + it_b_pay,
//				"seller_id=" + AlipayConfig.seller_id, "out_trade_no=" + out_trade_no, "subject=" + subject,
//				"body=" + body, "total_fee=" + total_fee, "payment_type=1", "notify_url=" + notify_url,
//				"show_url=" + show_url, "return_url=" + return_url, "_input_charset=" + AlipayConfig.input_charset };
//
//		// 支付URL生成
//		String aliay_url = AliPay.CreatUrl(gateway, // get方式传递参数时请去掉注释
//				para, AlipayConfig.input_charset, AlipayConfig.sign_type, AlipayConfig.private_key);
//
//		return aliay_url;
//	}


//	// 支付宝回调方法 同步
//	@RequestMapping(value = "returnUrl", method = RequestMethod.GET)
//	public void returnUrl(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		Map<String, String> params = new HashMap<String, String>();
//		Map requestParams = request.getParameterMap();
//		PrintWriter out = response.getWriter();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//			}
//			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			params.put(name, valueStr);
//		}
//
//		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//		// 商户订单号
//		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
//		// String out_trade_no = request.getParameter("out_trade_no");
//
//		// 支付宝交易号
//		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
//		// String trade_no = request.getParameter("trade_no");
//
//		// 交易状态
//		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
//		// String trade_status = request.getParameter("trade_status");
//
//		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//
//		// 计算得出通知验证结果
//		boolean verify_result = AlipayNotify.verify(params);
//
//		if (verify_result) {// 验证成功
//			//////////////////////////////////////////////////////////////////////////////////////////
//			// 请在这里加上商户的业务逻辑程序代码
//			// System.out.println("returnUrl 自己逻辑");
//			Order order = orderService.selectOrderByOrderNumber(out_trade_no);
//			long id = order.getOrderId();
//			long uid = order.getUserId();
//			// orderService.afterPay(id, trade_no);
//			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//			if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
//				// 判断该笔订单是否在商户网站中已经做过处理
//				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				// 如果有做过处理，不执行商户的业务程序
//			}
//
//			// 该页面可做页面美工编辑
//			System.out.println("验证成功");
//			out.println("success");
//			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//			response.sendRedirect("../orderFinish?uid=" + uid + "&id=" + id);
//			//////////////////////////////////////////////////////////////////////////////////////////
//		} else {
//			try {
//				StringBuffer error = new StringBuffer();
//				error.append("同步订单号:" + out_trade_no);
//				error.append("同步交易号:" + trade_no);
//				throw new Exception(error.toString());
//			} catch (Exception e) {
//				JSONObject json = new JSONObject();
//				json.putAll(requestParams);
//				log.error(e.getMessage() + " > " + json.toString());
//				System.out.println(e.getMessage());
//			} finally {
//				// 该页面可做页面美工编辑
//				out.println("fail");
//				System.out.println("验证失败");
//			}
//		}
//	}

//	// 支付宝回调方法 异步
//	@RequestMapping(value = "notifyUrl", method = RequestMethod.POST)
//	public void notifyUrl(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		Map<String, String> params = new HashMap<String, String>();
//		Map requestParams = request.getParameterMap();
//		PrintWriter out = response.getWriter();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//			}
//			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			// System.out.println(valueStr);
//			params.put(name, valueStr);
//		}
//
//		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//		// 商户订单号
//
//		// 商户订单号
//		String out_trade_no = request.getParameter("out_trade_no");
//
//		// 支付宝交易号
//		String trade_no = request.getParameter("trade_no");
//
//		// 交易状态
//		String trade_status = request.getParameter("trade_status");
//
//		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//
//		if (AlipayNotify.verify(params)) {// 验证成功
//			//////////////////////////////////////////////////////////////////////////////////////////
//			// 请在这里加上商户的业务逻辑程序代码
//
//			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//
//			if (trade_status.equals("TRADE_FINISHED")) {
//				// 判断该笔订单是否在商户网站中已经做过处理
//				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
//				// 如果有做过处理，不执行商户的业务程序
//				System.out.println("异步TRADE_FINISHED：订单号 > " + out_trade_no + "交易号：" + trade_no);
//				// 注意：
//				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//			} else if (trade_status.equals("TRADE_SUCCESS")) {
//				// 判断该笔订单是否在商户网站中已经做过处理
//				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
//				// 如果有做过处理，不执行商户的业务程序
//				Order order = orderService.selectOrderByOrderNumber(out_trade_no);
//				long id = order.getOrderId();
//				orderService.afterPay(id, trade_no);
//				System.out.println("异步TRADE_SUCCESS：订单号 > " + out_trade_no);
//				// 注意：
//				// 付款完成后，支付宝系统发送该交易状态通知
//			}
//
//			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//
//			System.out.println("success");
//			out.println("success"); // 请不要修改或删除
//
//			//////////////////////////////////////////////////////////////////////////////////////////
//		} else {// 验证失败
//			try {
//				StringBuffer error = new StringBuffer();
//				error.append("异步订单号:" + out_trade_no);
//				error.append("异步交易号:" + trade_no);
//				throw new Exception(error.toString());
//			} catch (Exception e) {
//				JSONObject json = new JSONObject();
//				json.putAll(requestParams);
//				log.error(e.getMessage() + " > " + json.toString());
//			} finally {
//				orderService.changOrderStatus(out_trade_no, -3);
//				System.out.println("fail");
//				out.println("fail");
//			}
//		}
//
//	}





}
