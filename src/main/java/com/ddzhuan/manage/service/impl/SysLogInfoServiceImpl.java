package com.ddzhuan.manage.service.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.enums.SysLogInfoEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.mysqldao.SysLogInfoDao;
import com.ddzhuan.manage.mysqlmodel.SysLogInfo;
import com.ddzhuan.manage.mysqlmodel.SysLogType;
import com.ddzhuan.manage.service.SysLogInfoService;
import com.ddzhuan.manage.tool.CacheTool;
import com.ddzhuan.manage.tool.SMSToolCL;

/**
 * 系统日志
 * @author wyp
 *
 */
@Service
public class SysLogInfoServiceImpl implements SysLogInfoService{

	Logger log = Logger.getLogger(SysLogInfoServiceImpl.class);
	
	@Autowired
	protected SysParam sysParam ;
	
	@Autowired
	private SysLogInfoDao sysLogInfoDao;
	
	@Autowired
	protected CacheTool cache;
	
	@Autowired
	protected SMSToolCL smsToolCL;
	
	@Override
	public int addSysLogInfo(SysLogInfo sysLog) {
		return sysLogInfoDao.insertSysLogInfo(sysLog);
	}
	
	public Map<Integer, SysLogType> getSysLogTypeMap(){
		Map<Integer, SysLogType> map = new HashMap<Integer, SysLogType>();
		List<SysLogType> list = sysLogInfoDao.querySysLogTypeList();
		if(list != null){
			for(SysLogType slt : list){
				map.put(slt.getLogTypeId(), slt);
			}
		}
		return map;
	}

	public int addSysLogInfoForTask(Integer logType, String actdesc) throws Exception{
		InetAddress ia = InetAddress.getLocalHost();
		String ip = ia.getHostAddress();
		String serverCode = sysParam.getSysLogServerCode(); //服务器编号（配置在服务端，也可以是服务器内网ip地址）
		if(serverCode == null || "".equals(serverCode)){
			serverCode = ip;
		}
		if(logType == null || logType.intValue() == 0){
			logType = SysLogInfoEnum.LOG_TYPE_SYSTEM_TASK.getValue();
		}
		SysLogInfo sysLog = new SysLogInfo();
		sysLog.setServerCode(serverCode);
		sysLog.setLogType(logType);
		sysLog.setObjKey(null);
		sysLog.setIp(ip);
		sysLog.setOperatorId(null);
		sysLog.setOperatorType(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue());
		sysLog.setActTime(new Date());
		sysLog.setActDesc(actdesc);
		sysLog.setActInfo(null);
		sysLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_INFO.getValue());
		return sysLogInfoDao.insertSysLogInfo(sysLog);
	}
	
	@Override
	public void processSysLogInfoCache() throws Exception{
		InetAddress ia = InetAddress.getLocalHost();
		String ip = ia.getHostAddress();
		ip = ip == null ? "" : ip;
		String serverCode = sysParam.getSysLogServerCode(); //服务器编号（配置在服务端，也可以是服务器内网ip地址）
		if(serverCode == null || "".equals(serverCode)){
			serverCode = ip;
		}
		List<String> keyList = cache.get("sysLogInfoCache").getKeys();
		Map<Integer, SysLogType> typeMap = getSysLogTypeMap();
		List<SysLogInfo> logList = new ArrayList<SysLogInfo>();
		
		for(String key : keyList){
			logList.add((SysLogInfo)cache.get("sysLogInfoCache", key));
			cache.remove("sysLogInfoCache", key);
		}
		
		LogThread thread = new LogThread(logList, ip, serverCode, typeMap, sysParam, sysLogInfoDao, this, smsToolCL);
		thread.start();
		
		/** 一下注释内容为无现成日志处理方式
		for(String key : keyList){
			SysLogInfo sysLog = (SysLogInfo)cache.get("sysLogInfoCache", key);
			try{
				if(sysLog != null){
					SysLogType type = typeMap.get(sysLog.getLogType().intValue());
						if(type == null){
							log.error("未定义的系统日志：actInfo=" + sysLog.getActInfo() + " logType=" + sysLog.getLogType() + " objKey=" + sysLog.getObjKey() + " ip=" + sysLog.getIp() + " operatorId=" + sysLog.getOperatorId() + " operatorType=" + sysLog.getOperatorType() + " actDesc=" + sysLog.getActDesc());
							continue;
						}else if(type.getActive() == null || type.getActive() <= 0){
							log.info("【"+sysLog.getLogType()+"】系统日志关闭：actInfo=" + sysLog.getActInfo() + " logType=" + sysLog.getLogType() + " objKey=" + sysLog.getObjKey() + " ip=" + sysLog.getIp() + " operatorId=" + sysLog.getOperatorId() + " operatorType=" + sysLog.getOperatorType() + " actDesc=" + sysLog.getActDesc());
							continue;
						}
						
						if(type.getAlertLevel() != null && type.getAlertLevel().indexOf(sysLog.getLogLevel()+"") >= 0 
								&& type.getAlertMobile() != null && type.getAlertMobile().length() >= 11){
							//todo 发送短信
							log.info("系统日志发送短信>>>>>>>" + sysLog.getActDesc() + ">>>>>>>>" + type.getAlertMobile());
						}
						
						sysLog.setServerCode(serverCode);
						sysLogInfoDao.insertSysLogInfo(sysLog);
				}
				count++;
			}catch(Exception ex){
				String errInfo = "";
				if(sysLog != null){
					errInfo = JSONObject.toJSONString(sysLog);
				}
				log.error("SYSLOG ERROR：" + errInfo);
				log.error(ex.getMessage(), ex);
				try{
					SysLogInfo errLog = new SysLogInfo();
					String serverCode = _p.getSysLogServerCode(); //服务器编号（配置在服务端，也可以是服务器内网ip地址）
					if(serverCode == null || "".equals(serverCode)){
						serverCode = ip;
					}
					errLog.setServerCode(serverCode);
					errLog.setLogType(0);
					errLog.setIp(ip);
					errLog.setOperatorType(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue());
					errLog.setActTime(new Date());
					errLog.setActDesc("缓存日志入库发生异常");
					errLog.setActInfo(errInfo);
					errLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_ERROR.getValue());
					sysLogInfoDao.insertSysLogInfo(sysLog);
				}catch(Exception e){
					log.error(e.getMessage(), e);
				}
			}finally{
				cache.remove("sysLogInfoCache", key);
			}
		}
		addSysLogInfoForTask(null, "缓存日志数据入库，本次任务总计："+keyList.size()+"条,完成："+count+"条");
		*/
	}

}

class LogThread extends Thread{
	
	private Logger log = Logger.getLogger(LogThread.class);
	
	private List<SysLogInfo> list;
	private String ip;
	private String serverCode;
	private Map<Integer, SysLogType> typeMap;
	private SysParam _p ;
	private SysLogInfoDao sysLogInfoDao;
	private SysLogInfoService sysLogInfoService;
	private SMSToolCL smsToolCL;
	
	public LogThread(List<SysLogInfo> list, String ip, String serverCode, Map<Integer, SysLogType> typeMap, 
			SysParam _p, SysLogInfoDao sysLogInfoDao, SysLogInfoService sysLogInfoService,SMSToolCL smsToolCL){
		this.list = list;
		this.ip = ip;
		this.serverCode = serverCode;
		this.typeMap = typeMap;
		this._p = _p;
		this.sysLogInfoDao = sysLogInfoDao;
		this.sysLogInfoService = sysLogInfoService;
		this.smsToolCL = smsToolCL;
	}
	
	public void run(){
		try{
			int count = 0;
			for(SysLogInfo sysLog : list){
				try{
					if(sysLog != null){
						SysLogType type = typeMap.get(sysLog.getLogType().intValue());
						if(type == null){
							log.error("线程：未定义的系统日志：actInfo=" + sysLog.getActInfo() + " logType=" + sysLog.getLogType() + " objKey=" + sysLog.getObjKey() + " ip=" + sysLog.getIp() + " operatorId=" + sysLog.getOperatorId() + " operatorType=" + sysLog.getOperatorType() + " actDesc=" + sysLog.getActDesc());
							continue;
						}else if(type.getActive() == null || type.getActive() <= 0){
							log.info("线程：【"+sysLog.getLogType()+"】系统日志关闭：actInfo=" + sysLog.getActInfo() + " logType=" + sysLog.getLogType() + " objKey=" + sysLog.getObjKey() + " ip=" + sysLog.getIp() + " operatorId=" + sysLog.getOperatorId() + " operatorType=" + sysLog.getOperatorType() + " actDesc=" + sysLog.getActDesc());
							continue;
						}
						
						if(type.getAlertLevel() != null && type.getAlertLevel().indexOf(sysLog.getLogLevel()+"") >= 0 
								&& type.getAlertMobile() != null && type.getAlertMobile().length() >= 11){
							//todo 发送短信
							log.info("线程：系统日志发送短信>>>>>>>" + sysLog.getActDesc() + ">>>>>>>>" + type.getAlertMobile());
							BaseResultInfo result = smsToolCL.SendSMS(_p, type.getAlertMobile(), sysLog.getActDesc());
							if(!result.isSuccess()){
								log.error("线程：系统日志发送短信失败>>>>>>>" + sysLog.getActDesc() + ">>>>>>>>" + type.getAlertMobile());
							}
						}else if(type.getAlertLevel() != null && type.getAlertLevel().indexOf(sysLog.getLogLevel()+"") >= 0 
								&& (type.getAlertMobile() == null || type.getAlertMobile().length() < 11)){
							log.error("线程：系统日志发送短信,手机号码错误>>>>>>>" + sysLog.getActDesc() + ">>>>>>>>" + type.getAlertMobile());
						}
						
						sysLog.setServerCode(serverCode);
						sysLogInfoDao.insertSysLogInfo(sysLog);
					}
					count++;
				}catch(Exception ex){
					String errInfo = "";
					if(sysLog != null){
						errInfo = JSONObject.toJSONString(sysLog);
					}
					log.error("线程：SYSLOG ERROR：" + errInfo);
					log.error(ex.getMessage(), ex);
					try{
						SysLogInfo errLog = new SysLogInfo();
						String serverCode = _p.getSysLogServerCode(); //服务器编号（配置在服务端，也可以是服务器内网ip地址）
						if(serverCode == null || "".equals(serverCode)){
							serverCode = ip;
						}
						errLog.setServerCode(serverCode);
						errLog.setLogType(0);
						errLog.setIp(ip);
						errLog.setOperatorType(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue());
						errLog.setActTime(new Date());
						errLog.setActDesc("线程：缓存日志入库发生异常");
						errLog.setActInfo(errInfo);
						errLog.setLogLevel(SysLogInfoEnum.LOG_LEVEL_ERROR.getValue());
						sysLogInfoDao.insertSysLogInfo(sysLog);
					}catch(Exception e){
						log.error(e.getMessage(), e);
					}
				}
			}
			sysLogInfoService.addSysLogInfoForTask(null, "线程：缓存日志数据入库，本次任务总计："+ list.size()+"条,完成："+count+"条");
		}catch(Exception ex){
			log.error("线程：系统日志线程异常");	
			log.error(ex.getMessage(), ex);	
		}
	}
}
