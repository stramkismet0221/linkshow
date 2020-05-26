package com.ddzhuan.manage.common.enums;
/**
 * 
 * @author jrj
 *
 */
public enum DDPFlowTypeEnum {
	
	LOGINWARD(1L, "登录奖励",2L),
	INVITEWARD(2L,"邀请奖励",10L),
	//BEINVITED(3L,"被邀请奖励",10L), 
	REGISTER(3L,"注册奖励",100L),
	SRUVEYAWARD(4L,"答卷喵力",0L),
	SIGN_IN_AWARD(5l, "签到", 2l),
	CONTINUE_SIGN_IN_AWARD(6l, "连续签到七天", 5l),
	LOGIN_SDWAN_AWARD(7l, "登录闪电玩", 1l),
	PLAYGAMEAWARD(8L,"玩游戏喵力",0L)
	;
	private Long flowtypeid;  //奖励类型id
	private String flowtypename;  //奖励类型名称
	private Long flownum; //奖励数量ddp
	private DDPFlowTypeEnum(Long flowtypeid,String flowtypename,Long flownum){
		this.flowtypeid=flowtypeid;
		this.flowtypename=flowtypename;
		this.flownum=flownum;
	}
	
	public Long getFlowtypeid() {
		return flowtypeid;
	}
	public void setFlowtypeid(Long flowtypeid) {
		this.flowtypeid = flowtypeid;
	}
	public String getFlowtypename() {
		return flowtypename;
	}
	public void setFlowtypename(String flowtypename) {
		this.flowtypename = flowtypename;
	}

	public Long getFlownum() {
		return flownum;
	}

	public void setFlownum(Long flownum) {
		this.flownum = flownum;
	}
	
}
