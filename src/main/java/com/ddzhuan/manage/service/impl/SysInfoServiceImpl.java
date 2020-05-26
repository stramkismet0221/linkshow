package com.ddzhuan.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddzhuan.manage.dao.SysInfoDAO;
import com.ddzhuan.manage.model.IPProvince;
import com.ddzhuan.manage.service.SysInfoService;
import com.ddzhuan.manage.tool.CacheTool;
import com.ddzhuan.manage.tool.IPTool;

@Service
public class SysInfoServiceImpl implements SysInfoService {
	
	@Autowired
	protected SysInfoDAO sysInfoDao;
	
	@Autowired
	protected CacheTool cache;
	

	@Override
	public IPProvince getIpPorvinceByIpLoong(String ip) {
		IPProvince ipp = null;
		Long iplong = IPTool.ip2Long(ip);
		if(iplong > 0){
			ipp = sysInfoDao.queryIpPorvinceByIpLoong(iplong);
		}
		return ipp;
	}
}
