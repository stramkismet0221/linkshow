package com.ddzhuan.manage.model;

import java.io.Serializable;

public class IPProvince implements Serializable{
	
	private String ipbegin;
	
	private String ipend;
	
	private String province;
	
	private String organization;
	
	private Long iplongbegin;
	
	private Long iplongend;
	
	private String city;
	
	private String prov;//纯的省份

	public String getIpbegin() {
		return ipbegin;
	}

	public void setIpbegin(String ipbegin) {
		this.ipbegin = ipbegin;
	}

	public String getIpend() {
		return ipend;
	}

	public void setIpend(String ipend) {
		this.ipend = ipend;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Long getIplongbegin() {
		return iplongbegin;
	}

	public void setIplongbegin(Long iplongbegin) {
		this.iplongbegin = iplongbegin;
	}

	public Long getIplongend() {
		return iplongend;
	}

	public void setIplongend(Long iplongend) {
		this.iplongend = iplongend;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	
}
