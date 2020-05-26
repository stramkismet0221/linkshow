package com.ddzhuan.manage.common.enums;

/**
 * 
 * @author jrj
 *
 */
public enum DDTokenFlowTypeEnum {

	FOUNDING_DDT(1, "创世奖励", 100.0),

	PICK_DDT(2, "收获喵钻", 0.0),

	SEC_FOUNDING_DDT(3, "创世二期空投", 20.0),
	
	THIRD_FOUNDING_DDT(4, "创世三期空投", 5.0),
	
	UPGRADE_DAPP_DDT(5, "版本更新", 0.1),
	
	SEVER_DAY_AWARD_DDT(6, "连续签到七天" , 2.0),
	
	SRUVEY_AWARD_DDT(7, "答卷喵钻", 0.0),
	
	USER_EXCHANGE_DDT(8, "喵钻提币", 0.0),
	
	USER_CANCEL_EXCHANGE_DDT(9, "取消提币", 0.0),
	
	USER_RED_ENVELOPE_DDT(10, "发红包", 0.0),
	
	USER_GRAB_RED_ENVELOPE_DDT(11, "抢红包", 0.0),
	
	USER_GIVE_BACK_RED_ENVELOPE_DDT(12, "退还红包余额", 0.0),
	
	USER_PAY_SDWAN_DDT(13, "用户支付奖励", 0.0),
	;
	private Integer flowType; // 奖励类型id

	private String flowTypeName; // 奖励类型名称

	private Double flowNum; // 奖励数量ddt

	private DDTokenFlowTypeEnum(Integer flowType, String flowTypeName, Double flowNum) {
		this.flowType = flowType;
		this.flowTypeName = flowTypeName;
		this.flowNum = flowNum;
	}

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	public Double getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(Double flowNum) {
		this.flowNum = flowNum;
	}

}
