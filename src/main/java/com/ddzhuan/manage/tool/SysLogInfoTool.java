package com.ddzhuan.manage.tool;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddzhuan.manage.common.enums.SysLogInfoEnum;
import com.ddzhuan.manage.mysqlmodel.SysLogInfo;

@Component
public class SysLogInfoTool {

	Logger log = Logger.getLogger(SysLogInfoTool.class);
	
	@Autowired
	protected CacheTool cache;

	/**
	 * DEBUG 日志<br/>
	 * @param actDesc//事件简述(必填，不能为 null 不能为 空串)<br/>
	 * @param logType//日志类型表主键 (必填，不能为 null 不能为 0)<br/>
	 * @param objKey//影响对象主键<br/>
	 * @param ip//事件发起方ip地址（如果有的话，如登陆、注册等用户访问）<br/>
	 * @param operatorId//操作人id (operatorType=1 时 null， 2时 userid， 3时 sysuserid)<br/>
	 * @param operatorType//1、系统自动；2、用户；3、管理员(必填，不能为 null 不能为 0)<br/>
	 * @param actionInfo//事件数据或详细内容<br/>
	 */
	public void debug(String actDesc, Integer logType, String objKey, String ip, String operatorId, Integer operatorType, String actInfo){
		SysLogInfo sysLog = createSysLog(logType, objKey, ip, operatorId, operatorType, actDesc, actInfo);
		if(sysLog == null){
			return;
		}
		sysLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_DEBUG.getValue());
		if(cache != null){
			cache.put("sysLogInfoCache", logType + "_" + sysLog.hashCode(), sysLog);
		}
		
	}
	
	/**
	 * INFO 日志
	 * @param actDesc//事件简述(必填，不能为 null 不能为 空串)<br/>
	 * @param logType//日志类型表主键 (必填，不能为 null 不能为 0)<br/>
	 * @param objKey//影响对象主键<br/>
	 * @param ip//事件发起方ip地址（如果有的话，如登陆、注册等用户访问）<br/>
	 * @param operatorId//操作人id (operatorType=1 时 null， 2时 userid， 3时 sysuserid)<br/>
	 * @param operatorType//1、系统自动；2、用户；3、管理员(必填，不能为 null 不能为 0)<br/>
	 * @param actionInfo//事件数据或详细内容<br/>
	 */
	public void info(String actDesc, Integer logType, String objKey, String ip, String operatorId, Integer operatorType, String actInfo){
		SysLogInfo sysLog = createSysLog(logType, objKey, ip, operatorId, operatorType, actDesc, actInfo);
		if(sysLog == null){
			return;
		}
		sysLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_INFO.getValue());
		if(cache != null){
			cache.put("sysLogInfoCache", logType + "_" + sysLog.hashCode(), sysLog);
		}
	}
	
	/**
	 * WARING 日志
	 * @param actDesc//事件简述(必填，不能为 null 不能为 空串)<br/>
	 * @param logType//日志类型表主键 (必填，不能为 null 不能为 0)<br/>
	 * @param objKey//影响对象主键<br/>
	 * @param ip//事件发起方ip地址（如果有的话，如登陆、注册等用户访问）<br/>
	 * @param operatorId//操作人id (operatorType=1 时 null， 2时 userid， 3时 sysuserid)<br/>
	 * @param operatorType//1、系统自动；2、用户；3、管理员(必填，不能为 null 不能为 0)<br/>
	 * @param actionInfo//事件数据或详细内容<br/>
	 */
	public void waring(String actDesc, Integer logType, String objKey, String ip, String operatorId, Integer operatorType, String actInfo){
		SysLogInfo sysLog = createSysLog(logType, objKey, ip, operatorId, operatorType, actDesc, actInfo);
		if(sysLog == null){
			return;
		}
		sysLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_WARING.getValue());
		if(cache != null){
			cache.put("sysLogInfoCache", logType + "_" + sysLog.hashCode(), sysLog);
		}
	}
	
	/**
	 * INFO 日志
	 * @param actDesc//事件简述(必填，不能为 null 不能为 空串)<br/>
	 * @param logType//日志类型表主键 (必填，不能为 null 不能为 0)<br/>
	 * @param objKey//影响对象主键<br/>
	 * @param ip//事件发起方ip地址（如果有的话，如登陆、注册等用户访问）<br/>
	 * @param operatorId//操作人id (operatorType=1 时 null， 2时 userid， 3时 sysuserid)<br/>
	 * @param operatorType//1、系统自动；2、用户；3、管理员(必填，不能为 null 不能为 0)<br/>
	 * @param actionInfo//事件数据或详细内容<br/>
	 */
	public void error(String actDesc, Integer logType, String objKey, String ip, String operatorId, Integer operatorType, String actInfo){
		SysLogInfo sysLog = createSysLog(logType, objKey, ip, operatorId, operatorType, actDesc, actInfo);
		if(sysLog == null){
			return;
		}
		sysLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_ERROR.getValue());
		if(cache != null){
			cache.put("sysLogInfoCache", logType + "_" + sysLog.hashCode(), sysLog);
		}
	}
	
	/**
	 * INFO 日志
	 * @param actDesc//事件简述(必填，不能为 null 不能为 空串)<br/>
	 * @param logType//日志类型表主键 (必填，不能为 null 不能为 0)<br/>
	 * @param objKey//影响对象主键<br/>
	 * @param ip//事件发起方ip地址（如果有的话，如登陆、注册等用户访问）<br/>
	 * @param operatorId//操作人id (operatorType=1 时 null， 2时 userid， 3时 sysuserid)<br/>
	 * @param operatorType//1、系统自动；2、用户；3、管理员(必填，不能为 null 不能为 0)<br/>
	 * @param actionInfo//事件数据或详细内容<br/>
	 */
	public void error(Throwable aThrowable, String actDesc, Integer logType, String objKey, String ip, String operatorId, Integer operatorType, String actInfo){
		if(actInfo == null){
			actInfo = "";
		}else{
			actInfo = "[提示信息]" + actInfo + "[提示信息]\n\n";
		}
		SysLogInfo sysLog = createSysLog(logType, objKey, ip, operatorId, operatorType, actDesc, actInfo + getExceptionStackTrace(aThrowable));
		if(sysLog == null){
			return;
		}
		sysLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_ERROR.getValue());
		if(cache != null){
			cache.put("sysLogInfoCache", logType + "_" + sysLog.hashCode(), sysLog);
		}
	}
	
	private SysLogInfo createSysLog(Integer logType, String objKey, String ip, String operatorId, Integer operatorType, String actDesc, String actInfo){
		if(logType == null || logType.intValue() == 0){
			log.error("SYSLOG CREATE ERROR：创建系统日志失败，日志类型为空！ ");
			log.error("日志内容: logType=" + logType + " objKey=" + objKey + " ip=" + ip + " operatorId=" + operatorId + " operatorType=" + operatorType + " actDesc=" + actDesc + " actInfo=" + actInfo);
			return null;
		}
		
		if(actDesc == null || "".equals(actDesc)){
			log.error("SYSLOG CREATE ERROR：创建系统日志失败，日志简述为空！ ");
			log.error("日志内容: logType=" + logType + " objKey=" + objKey + " ip=" + ip + " operatorId=" + operatorId + " operatorType=" + operatorType + " actDesc=" + actDesc + " actInfo=" + actInfo);
			return null;
		}
		
		if(operatorType == null || operatorType.intValue() == 0){
			operatorType = SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue();
		}
		
		SysLogInfo sysLog = new SysLogInfo();
		sysLog.setLogType(logType);
		sysLog.setObjKey(objKey);
		sysLog.setIp(ip);
		sysLog.setOperatorId(operatorId);
		sysLog.setOperatorType(operatorType);
		sysLog.setActTime(new Date());
		sysLog.setActDesc(actDesc);
		sysLog.setActInfo(actInfo);
		return sysLog;
	}
	
	/**
	 * 获取异常栈字符串
	 * @param aThrowable
	 * @return
	 */
	public String getExceptionStackTrace(Throwable aThrowable){
		if(aThrowable != null){
			Writer result = new StringWriter();
			PrintWriter printWriter = new PrintWriter(result);
			aThrowable.printStackTrace(printWriter);
			return result.toString();
		}else{
			return "";
		}
		
	}
	
	
}
