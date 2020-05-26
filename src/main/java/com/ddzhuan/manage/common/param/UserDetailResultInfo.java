package com.ddzhuan.manage.common.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class UserDetailResultInfo extends BaseResultInfo {
	private String portraitPhoto;
	private String email;
	private int sex;
	private String displayName;
	private String realName;
	private String birthday;
	private String mobile;
	private String payAccount;
	private String  modiFiedTime;
	private String payAccountUpdateTime;
	protected long manager;
	protected int isLeader;
	protected long  inviter;
	protected int leaderRank;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date teamJoinTime; 
	private int invitedNum;
	private int liveCity;//liveCity
	private String liveCityName;
	private int incumbency;
	private String incumbencyName;
	private int marriage;
	private String marriageName;
	private int degree;
	private String degreeName;
	

	public String getPayAccountUpdateTime() {
		return payAccountUpdateTime;
	}

	public void setPayAccountUpdateTime(String payAccountUpdateTime) {
		this.payAccountUpdateTime = payAccountUpdateTime;
	}

	public String getPortraitPhoto() {
		return portraitPhoto;
	}

	public void setPortraitPhoto(String portraitPhoto) {
		this.portraitPhoto = portraitPhoto;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getModiFiedTime() {
		return modiFiedTime;
	}

	public void setModiFiedTime(String modiFiedTime) {
		this.modiFiedTime = modiFiedTime;
	}

	public long getManager() {
		return manager;
	}

	public void setManager(long manager) {
		this.manager = manager;
	}

	public int getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}

	public long getInviter() {
		return inviter;
	}

	public void setInviter(long inviter) {
		this.inviter = inviter;
	}

	public int getLeaderRank() {
		return leaderRank;
	}

	public void setLeaderRank(int leaderRank) {
		this.leaderRank = leaderRank;
	}

	public Date getTeamJoinTime() {
		return teamJoinTime;
	}

	public void setTeamJoinTime(Date teamJoinTime) {
		this.teamJoinTime = teamJoinTime;
	}

	public int getInvitedNum() {
		return invitedNum;
	}

	public void setInvitedNum(int invitedNum) {
		this.invitedNum = invitedNum;
	}

	public int getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(int liveCity) {
		this.liveCity = liveCity;
	}

	public int getIncumbency() {
		return incumbency;
	}

	public void setIncumbency(int incumbency) {
		this.incumbency = incumbency;
	}

	public int getMarriage() {
		return marriage;
	}

	public void setMarriage(int marriage) {
		this.marriage = marriage;
	}

	public String getLiveCityName() {
		return liveCityName;
	}

	public void setLiveCityName(String liveCityName) {
		this.liveCityName = liveCityName;
	}

	public String getIncumbencyName() {
		return incumbencyName;
	}

	public void setIncumbencyName(String incumbencyName) {
		this.incumbencyName = incumbencyName;
	}

	public String getMarriageName() {
		return marriageName;
	}

	public void setMarriageName(String marriageName) {
		this.marriageName = marriageName;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

}
