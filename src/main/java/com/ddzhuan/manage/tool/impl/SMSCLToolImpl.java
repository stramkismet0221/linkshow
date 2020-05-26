package com.ddzhuan.manage.tool.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.tool.SMSToolCL;
import com.ddzhuan.manage.tool.clsms.ChuangLanSmsUtil;
import com.ddzhuan.manage.tool.clsms.request.SmsSendRequest;
import com.ddzhuan.manage.tool.clsms.response.SmsSendResponse;

/**
 * 创蓝短信接口
 * 
 * @author loki
 *
 */
@Component
public class SMSCLToolImpl implements SMSToolCL {

	private Log log = LogFactory.getLog("SMSCLTool");

	@Override
	public BaseResultInfo SendSMS(SysParam p, String mobiles, String msg) {
		BaseResultInfo ret = new BaseResultInfo();
		ret.setSuccess(false);
		ret.setMsg("未能发送");
		String res = "";
		String clUrl = p.getSmsCLUrl();;
		
		// 将发送接口设为空字符串可以屏蔽短信发送
		if (clUrl == null || "".equals(clUrl)) {// 短信危机
			ret.setSuccess(false);
			ret.setMsg("发送失败");
			log.error("紧急情况，屏蔽DDZ_CL发送接口【" + mobiles + "】" + res);
			return ret;
			// 短信危机
		}
				
		SmsSendRequest smsSingleRequest = new SmsSendRequest(p.getSmsCLAccount(), p.getSmsCLPwd(), msg, mobiles, "true");
		String requestJson = JSON.toJSONString(smsSingleRequest);
		String response = ChuangLanSmsUtil.sendSmsByPost(clUrl, requestJson);
		SmsSendResponse smsResp = JSON.parseObject(response, SmsSendResponse.class);
		
		if("0".equals(smsResp.getCode()) && "".equals(smsResp.getErrorMsg())){
			ret.setSuccess(true);
			ret.setMsg("发送成功");
		}else{
			ret.setSuccess(false);
			ret.setMsg("发送失败");
			log.error("未能发送【" + mobiles + "】" + res);
		}
		
		return ret;
	}

	public static void main(String[] args) {
		//短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
		String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
		
		// 设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
	    String msg = "点点星球123";
		//手机号码
		String phone = "13917775269";
		//状态报告
		String report= "true";
		
		SmsSendRequest smsSingleRequest = new SmsSendRequest("M2706661", "J4srWAgEc0d369", msg, phone,report);
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		System.out.println("before request string is: " + requestJson);
		
		String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		
		System.out.println("response after request result is :" + response);
		
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		System.out.println("response  toString is :" + smsSingleResponse);
	}

}
