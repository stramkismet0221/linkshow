package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.common.param.SMSResultInfo;

public interface ToolService {
	
	public BaseResultInfo sendSms(long userId,SysParam config,String mobiles, String ip, String msg,String logType,String oCode);

	public SMSResultInfo sendSmsForTool(SysParam p, String mobiles, String msg);
	public BaseResultInfo sendSms(SysParam config, String mobiles, String ip, String msg);
}
