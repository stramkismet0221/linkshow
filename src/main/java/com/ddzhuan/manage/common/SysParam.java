package com.ddzhuan.manage.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:conf/param.properties")
public class SysParam {

    /////////创蓝短信网关参数///////////
    @Value("${smsCLUrl}")
    private String smsCLUrl;
    @Value("${smsCLAccount}")
    private String smsCLAccount;
    @Value("${smsCLPwd}")
    private String smsCLPwd;

    //系统日志中服务标识符 serverCode
    @Value("${sysLogServerCode}")
    private String sysLogServerCode;

    //DAPP WEB服务
    @Value("${wdappURL}")
    private String wdappURL;

  //华信短信接口参数
    @Value("${smsHuaXinAccount}")
  	private String smsHuaXinAccount;
    @Value("${smsHuaXinPwd}")
  	private String smsHuaXinPwd;
    @Value("${smsHuaXinUrl}")
  	private String smsHuaXinUrl;
    @Value("${currServerNo}")
  	private String currServerNo;
    
    
    
    
    
    
    
    public String getSmsCLUrl() {
        return smsCLUrl;
    }

    public void setSmsCLUrl(String smsCLUrl) {
        this.smsCLUrl = smsCLUrl;
    }

    public String getSmsCLAccount() {
        return smsCLAccount;
    }

    public void setSmsCLAccount(String smsCLAccount) {
        this.smsCLAccount = smsCLAccount;
    }

    public String getSmsCLPwd() {
        return smsCLPwd;
    }

    public void setSmsCLPwd(String smsCLPwd) {
        this.smsCLPwd = smsCLPwd;
    }

    public String getSysLogServerCode() {
        return sysLogServerCode;
    }

    public void setSysLogServerCode(String sysLogServerCode) {
        this.sysLogServerCode = sysLogServerCode;
    }

    public String getWdappURL() {
        return wdappURL;
    }

    public void setWdappURL(String wdappURL) {
        this.wdappURL = wdappURL;
    }

	public String getSmsHuaXinAccount() {
		return smsHuaXinAccount;
	}

	public void setSmsHuaXinAccount(String smsHuaXinAccount) {
		this.smsHuaXinAccount = smsHuaXinAccount;
	}

	public String getSmsHuaXinPwd() {
		return smsHuaXinPwd;
	}

	public void setSmsHuaXinPwd(String smsHuaXinPwd) {
		this.smsHuaXinPwd = smsHuaXinPwd;
	}

	public String getSmsHuaXinUrl() {
		return smsHuaXinUrl;
	}

	public void setSmsHuaXinUrl(String smsHuaXinUrl) {
		this.smsHuaXinUrl = smsHuaXinUrl;
	}

	public String getCurrServerNo() {
		return currServerNo;
	}

	public void setCurrServerNo(String currServerNo) {
		this.currServerNo = currServerNo;
	}

    
}
