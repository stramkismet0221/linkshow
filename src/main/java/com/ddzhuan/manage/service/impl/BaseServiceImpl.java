package com.ddzhuan.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.common.param.SMSResultInfo;
import com.ddzhuan.manage.dao.BaseDao;
import com.ddzhuan.manage.service.BaseService;
import com.ddzhuan.manage.tool.SMSToolCL;


@Service
public class BaseServiceImpl implements BaseService{

	@Autowired
	protected BaseDao baseDao;

	@Autowired
	protected SMSToolCL smsToolCL;
	
	@Override
	public void test(Long uid){
		System.out.println(uid);
		baseDao.querySysDateTime();
	}
	
	protected SMSResultInfo sendSms(SysParam p, String mobiles, String msg) {
		BaseResultInfo result = new BaseResultInfo();
		SMSResultInfo ret=new SMSResultInfo();

		//点点赚短息接口使用创蓝
		result = smsToolCL.SendSMS(p, mobiles, msg);
		ret.setSuccess(result.isSuccess());
		ret.setMsg(result.getMsg());
		ret.setShortSimpleName(smsToolCL.getClass().getSimpleName());
		return ret;
	}
}
