package com.ddzhuan.manage.mysqldao;

import java.util.List;

import com.ddzhuan.manage.mysqlmodel.SysLogInfo;
import com.ddzhuan.manage.mysqlmodel.SysLogType;

public interface SysLogInfoDao {

	public int insertSysLogInfo(SysLogInfo sysLog);
	
	public List<SysLogType> querySysLogTypeList();
	
}
