package com.ddzhuan.manage.tool;

import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.BaseResultInfo;

public interface SMSToolHuaXin {
	
	public BaseResultInfo SendSMS(SysParam p, String mobiles, String msg);
	
}
