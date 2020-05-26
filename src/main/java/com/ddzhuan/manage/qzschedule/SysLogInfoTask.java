package com.ddzhuan.manage.qzschedule;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddzhuan.manage.service.SysLogInfoService;

/**
 * 系统日志缓存日志入库驱动程序任务计划
 * @author 123
 *
 */
public class SysLogInfoTask {

	Logger log = Logger.getLogger(SysLogInfoTask.class);
	
	@Autowired
	private SysLogInfoService sysLogInfoService;
	
	public void run(){
		try{
			sysLogInfoService.processSysLogInfoCache();
		}catch(Exception ex){
			log.error("系统日志缓存日志入库驱动程序任务计划异常...");
			log.error(ex.getMessage(), ex);
		}
		
		
	}
	
}
