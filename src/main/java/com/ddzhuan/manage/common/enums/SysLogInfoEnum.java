package com.ddzhuan.manage.common.enums;

/**
 * 系统日志
 * @author wyp
 *
 */
public enum SysLogInfoEnum {
	
	//日志等级
	LOG_LEVEL_DEBUG(0, "DEBUG"),//调试级别
	
	LOG_LEVEL_INFO(1, "INFO"),//信息
	
	LOG_LEVEL_WARING(2, "WARING"),//警告
	
	LOG_LEVEL_ERROR(3, "ERROR"),//异常
	
	
	//日志操作人类型
	LOG_OPERATOR_TYPE_SYSTEM(1,"SYSTEM"),//系统自动日志
	
	LOG_OPERATOR_TYPE_USER(2,"USER"),//用户日志
	
	LOG_OPERATOR_TYPE_SYSUSER(3,"SYSUSER"),//系统操作员日志
	
	//日志类型
	LOG_TYPE_SYSTEM_TASK(1,"SYSTEM_TASK_LOG"),//系统任务计划
	
	LOG_TYPE_USER_LOGIN(2,"USER_LOGIN_LOG"),//用户登陆日志
	
	LOG_TYPE_USER_REGEST(3,"USER_REGEST_LOG"),//用户注册日志
	
	LOG_TYPE_DDTOKEN_SEED(4,"DDTOKEN_SEED_LOG"),//喵钻生成日志
	
	LOG_TYPE_PHONEIX_DDTOKEN_ACC(5,"PHONEIX_DDTOKEN_ACC"),//塔链phoenix账本日志
	
	LOG_TYPE_USER_SURVEY_ANSWER(6,"USER_SURVEY_ANSWER"),//用户答卷日志
	
	LOG_TYPE_USER_WALLET(7,"USER_WALLET_LOG"),//用户钱包日志
	
	LOG_TYPE_USER_REDENVELOPE(8,"USER_REDENVELOPE_LOG"),//红包业务包日志
	;

	private Integer value;
	
	private String code;
	
	private SysLogInfoEnum(Integer value, String code){
		this.value = value;
		this.code = code;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



}

	