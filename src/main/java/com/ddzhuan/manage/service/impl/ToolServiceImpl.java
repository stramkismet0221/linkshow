package com.ddzhuan.manage.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.common.param.SMSResultInfo;
import com.ddzhuan.manage.dao.SysInfoDAO;
import com.ddzhuan.manage.model.SysLog;
import com.ddzhuan.manage.service.ToolService;
import com.ddzhuan.manage.tool.SMSToolHuaXin;

@Service
public class ToolServiceImpl extends BaseServiceImpl  implements ToolService {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	protected SysInfoDAO sysInfoDao;
	@Autowired
	protected SMSToolHuaXin smsToolHuaXin;
	/**
	 * 发送短信接口
	 */
	@Override
	public BaseResultInfo sendSms(long userId, SysParam config, String mobiles, String ip, 
			String msg,String logType,String ocode) {
		BaseResultInfo result = new BaseResultInfo();
		result.setSuccess(false);
		result.setMsg("发送失败");
		// 检查短信发送要求
		// 一人90秒内一次，一天不超过5次
		SysLog sysLog = new SysLog();
		sysLog.setUserName(mobiles);
		sysLog.setUserId(userId);
		sysLog.setIpAddr(ip);
		sysLog.setLogType(logType);
		List<SysLog> mobileLogs = sysInfoDao.querySmsLogByMobile(sysLog);
		if (mobileLogs != null && mobileLogs.size() > 5) {
			// 一天超过限制
			log.error(String.format("号码【%s】超过当天短信最高限制", mobiles));
			result.setSuccess(false);
			result.setMsg("您今天已经发了过多的短信请明天再来试试。");
			return result;
		}
		SMSResultInfo ret=new SMSResultInfo();
		ret= sendSms(config, mobiles, msg);
		result.setMsg(ret.getMsg());
		result.setSuccess(ret.isSuccess());
		sysLog = new SysLog();
		sysLog.setContent(ret.getShortSimpleName());
		sysLog.setUserId(userId);
		sysLog.setUserName(mobiles);
		sysLog.setLogType(logType);
		sysLog.setResult(String.valueOf(result.isSuccess()));
		sysInfoDao.inserSystemLog(sysLog);
		return result;
	}

	public SMSResultInfo sendSmsForTool(SysParam p, String mobiles, String msg){
		return sendSms(p, mobiles, msg);
	}
	@Override
	public BaseResultInfo sendSms(SysParam config, String mobiles, String ip, String msg) {
		BaseResultInfo result = new BaseResultInfo();
		result.setSuccess(false);
		result.setMsg("发送失败");
		// 检查短信发送要求
		// 一人90秒内一次，一天不超过5次
		SysLog sysLog = new SysLog();
		sysLog.setUserName(mobiles);
		sysLog.setIpAddr(ip);
		List<SysLog> mobileLogs = sysInfoDao.querySmsLogByMobile(sysLog);
		if (mobileLogs.size()> 5) {
			// 一天超过限制
			log.error(String.format("号码【%s】超过当天短信最高限制", mobiles));
			result.setSuccess(false);
			result.setMsg("您当日发送短信数目已满，请明天再来。");
			return result;
		}
		// 发送
		SMSResultInfo ret=new SMSResultInfo();
		ret= sendSms(config, mobiles, msg);
		 result.setMsg(ret.getMsg());
		 result.setSuccess(ret.isSuccess());
		// 记录日志
		sysLog = new SysLog();
		sysLog.setContent(ret.getShortSimpleName());
		sysLog.setUserName(mobiles);
		sysLog.setResult(String.valueOf(result.isSuccess()));
		sysLog.setIpAddr(ip);
		sysLog.setLogType("");
		sysInfoDao.inserSystemLog(sysLog);
		return result;
	}

	protected SMSResultInfo sendSms(SysParam p, String mobiles, String msg) {
		BaseResultInfo result = new BaseResultInfo();
		SMSResultInfo ret=new SMSResultInfo();
		result=smsToolCL.SendSMS(p, mobiles, msg);
		ret.setSuccess(result.isSuccess());
		ret.setMsg(result.getMsg());
		ret.setShortSimpleName(smsToolCL.getClass().getSimpleName());
		if (result.isSuccess() == false) {
			result = smsToolHuaXin.SendSMS(p, mobiles, msg);
			ret.setSuccess(result.isSuccess());
			ret.setMsg(result.getMsg());
			ret.setShortSimpleName(smsToolHuaXin.getClass().getSimpleName());
		}
		return ret;
	}
}
