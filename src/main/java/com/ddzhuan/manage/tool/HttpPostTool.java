package com.ddzhuan.manage.tool;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpPostTool {

	private Log log = LogFactory.getLog("HttpPostTool");

	public String convertUnicode2UTF(String utfString) {
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = utfString.indexOf("\\u", pos)) != -1) {
			sb.append(utfString.substring(pos, i));
			if (i + 5 < utfString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
			}
		}
		return sb.toString();
	}

	/**
	 * 无证书授信HTTPS
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault() {
		CloseableHttpClient httpClient;
		SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			}).build();
		} catch (NoSuchAlgorithmException e) {
			e.getStackTrace();
		} catch (KeyManagementException e) {
			e.getStackTrace();
		} catch (KeyStoreException e) {
			e.getStackTrace();
		}
		httpClient = HttpClients.custom().setSslcontext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

		return httpClient;
	}

	/**
	 * get方式获得请求String
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            预留字段
	 * @return
	 */
	public String get(String url) {

		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * get方式获得请求String
	 * @param url 请求地址
	 * @param params 预留字段
	 * @return
	 */
	public String sslGet(String url) {
		String result = "";
		CloseableHttpClient httpclient = createSSLClientDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public String post(String url, Map<String, Object> params) {
		String result = "";
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, Object> item : params.entrySet()) {
				nvps.add(new BasicNameValuePair(item.getKey(), String.valueOf(item.getValue())));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			try {
				CloseableHttpResponse response = httpclient.execute(httpPost);
				try {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity);
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
	public String postHeader(String url, Map<String, Object> params,  Map<String, Object> headparams) {
		String result = "";
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, Object> item : params.entrySet()) {
				nvps.add(new BasicNameValuePair(item.getKey(), String.valueOf(item.getValue())));
			}
			for (Entry<String, Object> item : headparams.entrySet()) {
				httpPost.addHeader(item.getKey(), String.valueOf(item.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			try {
				CloseableHttpResponse response = httpclient.execute(httpPost);
				try {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity);
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static void main(String[] args) {
		HttpPostTool ht = new HttpPostTool();
		String url =String.format( "http://odianyun.com/open-api/newToken.do?appId=%s&appSecret=%s","654a2cb20150813094746","6551c0e56b3620150813094746");
		String sendurl = "http://odianyun.com/open-api/omc/sendTask.do";
		String result =ht.get(url);
		JSONObject resultObj = JSON.parseObject(result);
		boolean booleanValue = resultObj.getBooleanValue("success");
		if(booleanValue){
			JSONObject dataObj = resultObj.getJSONObject("data");
			String appToken=dataObj.getString("appToken");
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("token", appToken);
			JSONObject sendTaskApi=new JSONObject();
			sendTaskApi.put("taskNo","MC6520151008164514");
			sendTaskApi.put("signatureNo", "20150813143943");
			sendTaskApi.put("chanelTypeId", 1);
			sendTaskApi.put("originalID", new String[]{"1"});
			sendTaskApi.put("mobiles", new String[]{"15618960801"});
			JSONObject content =new JSONObject();
			content.put("content", "欢迎您使用点点赚");
			sendTaskApi.put("contentVar", content);
			params.put("sendTaskApiDto", sendTaskApi.toJSONString());
			String sendResult=ht.post(sendurl, params);
		
			JSONObject sendObj= JSON.parseObject(sendResult);
			if(sendObj.getBooleanValue("success")){
				System.out.println("发送成功");
			}else{
				System.out.println("发送失败");
			}
			
		}
		
//		HashMap<String, Object> params = new HashMap<String, Object>();
//		params.put("action", "send");
//		params.put("account", "xd000832");
//		params.put("password", "xd000832123");
//		params.put("mobile", "15618960801"); 
//		params.put("content", String.format("%s【点点赚】", "尊敬的点点赚会员您好，祝您周末愉快。"));
//		String result = ht.post(url, params);
//		System.out.println("result:" + result);
	}
}
