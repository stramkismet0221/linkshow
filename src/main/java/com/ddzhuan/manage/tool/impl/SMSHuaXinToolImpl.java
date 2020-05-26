package com.ddzhuan.manage.tool.impl;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.tool.HttpPostTool;
import com.ddzhuan.manage.tool.SMSToolHuaXin;


@Component
public class SMSHuaXinToolImpl implements SMSToolHuaXin {

	private Log log = LogFactory.getLog("HuaXinSmsTool");



	@Override
	public BaseResultInfo SendSMS(SysParam p, String mobiles, String msg) {
		BaseResultInfo ret = new BaseResultInfo();
		ret.setSuccess(false);
		ret.setMsg("未能发送");
		String res = "";
		HttpPostTool postTool =new HttpPostTool();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("action", "send");
		params.put("mobile", mobiles);
		String huaXinUrl = p.getSmsHuaXinUrl();
		params.put("account", p.getSmsHuaXinAccount());
		params.put("password", p.getSmsHuaXinPwd());
		params.put("content", String.format("%s【点点赚】", msg));
		//将发送接口设为空字符串可以屏蔽短信发送
		if(huaXinUrl == null || "".equals(huaXinUrl)){
			//短信危机
			ret.setSuccess(false);
			ret.setMsg("发送失败");
			log.error("紧急情况，屏蔽DDZ_HUAXIN发送接口【" + mobiles + "】" + res);
			return  ret;
			//短信危机
		}
		
		res=postTool.post(huaXinUrl, params);
		if (res != null && res.indexOf("<returnstatus>Success</returnstatus>") > 0) {
			ret.setSuccess(true);
			ret.setMsg("发送成功");
		} else {
			ret.setSuccess(false);
			ret.setMsg("发送失败");
			log.error("未能发送【" + mobiles + "】" + res);
		}
	return  ret;

	}
}
