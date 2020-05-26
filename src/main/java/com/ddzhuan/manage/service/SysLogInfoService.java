package com.ddzhuan.manage.service;

import com.ddzhuan.manage.mysqlmodel.SysLogInfo;

public interface SysLogInfoService {

	public int addSysLogInfo(SysLogInfo sysLog);
	
	public int addSysLogInfoForTask(Integer logType, String actdesc) throws Exception;
	
	public void processSysLogInfoCache() throws Exception;
	
}
