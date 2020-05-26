package com.ddzhuan.manage.model;

import java.io.Serializable;
import java.util.Date;

public class YouPengOrder implements Serializable{
	
	private String ypreceiptno;//友朋订单号
	
	private String tradeno;//支付渠道订单号
	
	private int paytype;//支付方式，0支付宝，1微信支付
	
	private int orderstatus;//订单状态，0支付成功，1支付失败，2退款成功，3退款失败

	private int outstatus; // 出货状态：0、成功 1、失败 2、支付失败时出货失败

	private Date createtime; // 创建时间

	private Date modifytime; // 修改时间

	public String getYpreceiptno() {
		return ypreceiptno;
	}

	public void setYpreceiptno(String ypreceiptno) {
		this.ypreceiptno = ypreceiptno;
	}

	public String getTradeno() {
		return tradeno;
	}

	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	public int getOutstatus() {
		return outstatus;
	}

	public void setOutstatus(int outstatus) {
		this.outstatus = outstatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
}
