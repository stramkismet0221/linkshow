package com.ddzhuan.manage.tool.weixinpay;

import com.ddzhuan.manage.tool.weixinpay.WXPayConfig;
import java.io.*;
import java.util.Map;

public class MyConfig extends WXPayConfig{

    private byte[] certData;
    public MyConfig() throws Exception {
        String certPath = "/cert/apiclient_cert.p12";
        File file = new File(this.getClass().getClassLoader().getResource(certPath).getFile());
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return "wxaf5ca6dac073c671";
    }

    public String getMchID() {
        return "1254161001";
    }

    public String getKey() {
        return "8dKjl65987DfH46jv89Mycl6K509bVMd";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
//    	return null;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

	@Override
	public IWXPayDomain getWXPayDomain() { // 这个方法需要这样实现, 否则无法正常初始化WXPay
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
 
            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }

}
