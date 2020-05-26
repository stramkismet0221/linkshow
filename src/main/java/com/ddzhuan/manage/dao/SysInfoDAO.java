package com.ddzhuan.manage.dao;

import java.util.List;

import com.ddzhuan.manage.model.IPProvince;
import com.ddzhuan.manage.model.SysLog;

public interface SysInfoDAO {
	
	//插入记录
	public void inserSystemLog(SysLog sysLog);
	
	//查询用户短信日志
	public List<SysLog> querySmsLogByMobile(SysLog sysLog);

	//根据ip查询日志
	public List<SysLog> querySmsLogByIp(SysLog sysLog);
	
	
	public IPProvince queryIpPorvinceByIpLoong(long ipLong);
	
}
