package com.ddzhuan.manage.common.param;

import java.io.Serializable;

public class YouPengResultInfo implements Serializable {
	
	//错误代码，0表示正常，!=0异常错误
	protected Integer error_code;
	
	//错误描述
	protected String error_msg;

	private Object refundsRawData;

	private String refundsTime;

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public Object getRefundsRawData() {
		return refundsRawData;
	}

	public void setRefundsRawData(Object refundsRawData) {
		this.refundsRawData = refundsRawData;
	}

	public String getRefundsTime() {
		return refundsTime;
	}

	public void setRefundsTime(String refundsTime) {
		this.refundsTime = refundsTime;
	}
}
