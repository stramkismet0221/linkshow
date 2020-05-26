package com.ddzhuan.manage.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ddzhuan.manage.common.param.BaseResultInfo;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import cn.ucloud.ufile.HmacSHA1;

public class UploadFileTool {

	static private UploadFileTool instance;
	
	private Map<String, String> fileContentTypeMap;
	
	
	
	static public UploadFileTool getInstance(){
		if(instance == null){
			instance = new UploadFileTool();
		}
		return instance;
	}
	
	String uri = "http://ddzimg.ufile.ucloud.cn/";
	String public_key = "n7nAgd7bwYhX5hGfpfMCVOfr6sqir5BNg78+i6JwBsEXqCMExeG4MA==";//写入配置参数
	String private_key = "76b87b2c85d336d3367e552a6846bc00a5348490";//写入配置参数
	String ufileCdnDomain = "http://ddzimg.ufile.ucloud.com.cn";
	String ufile_bucket = "ddzimg";

	/**
	 * 用UFILE的PUT方式上传文件，需要提供文件的输入流，需要放在UFILE上的路径，文件名（必须有文件后缀，用于确定文件类型http的contentType）
	 * exsample 上传到URILE的/test/test.jpg
	 * filePath="test"
	 * fileName="test.jpg"
	 * 
	 * @param inputStream
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public String upLoadWithUFilePut(InputStream inputStream, String filePath, String fileName) throws Exception{
			String fileUrl = null;

			String suffix = fileName.split("\\.")[fileName.split("\\.").length-1];
			String http_method = "PUT";
			String content_md5 = "";
			String content_type = getFileContentType(suffix);//"image/jpeg";//"text/plain";
			if(content_type == null || "".equals(content_type)){
				throw new Exception("UploadFileTool file contentType not exist!");
			}
			String date = "";
			String canonicalized_ucloud_headers = "x-ucloud-hello" + ":" + "hello" + "\n" +
					"x-ucloud-world" + ":" + "world" + "\n";
			String bucket = ufile_bucket;//"helloworld";
			String key = filePath + "/" + fileName;//"test-key";
			String canonicalized_resource = "/" + bucket + "/" + key;
			
			String str_to_sign =  http_method + "\n" + content_md5 + "\n" + content_type + "\n" + date + "\n" +
					 canonicalized_ucloud_headers + canonicalized_resource;
			String signature = new HmacSHA1().sign(private_key, str_to_sign);
			System.out.println("http_method: " + http_method);
			System.out.println("key: " + key);
			System.out.println("bucket: " + bucket);
			System.out.println("str_to_sign: " + signature);
			System.out.println("signature: " + signature);
			
			String authorization = "UCloud" + " " + public_key + ":" + signature;
			
			System.out.println("authorization: " + authorization);
			String uri = "http://" + bucket + ".ufile.ucloud.cn/" + key;
			System.out.println("uri: " + uri);
			HttpPut putMethod = new HttpPut(uri);
			
			putMethod.setHeader("Content-Type", content_type);
			putMethod.setHeader("Authorization", authorization);
			putMethod.setHeader("X-UCloud-Hello", "hello");
			putMethod.setHeader("X-UCloud-World", "world");
			long length = inputStream.available();

			InputStreamEntity entity = new InputStreamEntity(inputStream,  length);

			putMethod.setEntity(entity);
	
			HttpClient client = new DefaultHttpClient();
			//client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
			HttpResponse response = client.execute(putMethod);
			HttpEntity resEntity = response.getEntity();
			System.out.println(response.getStatusLine());
			if (resEntity != null) {
				System.out.println("Response content length: " + resEntity.getContentLength());
				InputStream is = resEntity.getContent(); 
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String s = "";
				while ((s = reader.readLine()) != null) {
					System.out.println(s);
				}
			}
			client.getConnectionManager().shutdown();
			fileUrl = ufileCdnDomain + "/" + key;
		
		return fileUrl;
	}
	
	/**
	 * 删除优云上的文件
	 * exsample 上传到URILE的http://ddzimg.ufile.ucloud.com.cn/test/test.jpg
	 * 
	 * @param ufileCdnUrl
	 * @return
	 */
	public boolean deleteWithUFileDelete(String ufileCdnUrl) throws Exception{
		boolean flg = false;
		String filePath = ufileCdnUrl.replace("http://ddzimg.ufile.ucloud.com.cn/", "");
			String http_method = "DELETE";
			String content_md5 = "";
			String content_type = "";
			String date = "";
			String canonicalized_ucloud_headers = "";
			String bucket = ufile_bucket;//"orange";
			String key = filePath;//"test-key";
			String canonicalized_resource = "/" + bucket + "/" + key;
			
			String str_to_sign =  http_method + "\n" + content_md5 + "\n" + content_type + "\n" + date + "\n" +
					 canonicalized_ucloud_headers + canonicalized_resource;
			String signature = new HmacSHA1().sign(private_key, str_to_sign);
			System.out.println("signature: " + signature);
			
			String authorization = "UCloud" + " " + public_key + ":" + signature;
			
			System.out.println("authorization: " + authorization);
			
			String uri = "http://" + bucket + ".ufile.ucloud.com.cn/" + key;
			HttpDelete putMethod = new HttpDelete(uri);
		
			putMethod.setHeader("Authorization", authorization);
			
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(putMethod);
			System.out.println(response);
			HttpEntity resEntity = response.getEntity();
			
			System.out.println(response.getStatusLine());
			if (resEntity != null) {
				System.out.println("Response content length: " + resEntity.getContentLength());
				InputStream is = resEntity.getContent(); 
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String s = "";
				while ((s = reader.readLine()) != null) {
					System.out.println(s);
					flg = s.indexOf("HTTP/1.1 204 No Content") >= 0;
					if(flg){
						break;
					}
					
				}
			}
			client.getConnectionManager().shutdown();
		
		return flg;
	}
	
	public void testput(InputStream inputStream, String fileName) throws Exception{
		
		System.out.println("available>>"+inputStream.available());
		String public_key = "n7nAgd7bwYhX5hGfpfMCVOfr6sqir5BNg78+i6JwBsEXqCMExeG4MA==";//写入配置参数
		String private_key = "76b87b2c85d336d3367e552a6846bc00a5348490";//写入配置参数
		
		
		String http_method = "PUT";
		String content_md5 = "";
		String content_type = "text/plain";
		String date = "";
		String canonicalized_ucloud_headers = "x-ucloud-hello" + ":" + "hello" + "\n" +
				"x-ucloud-world" + ":" + "world" + "\n";
		//String canonicalized_ucloud_headers = "";
		String bucket = "ddzimg";
		String key = "test.jpg";
		String canonicalized_resource = "/" + bucket + "/" + key;
		
		String str_to_sign =  http_method + "\n" + content_md5 + "\n" + content_type + "\n" + date + "\n" +
				 canonicalized_ucloud_headers + canonicalized_resource;
		String signature = new HmacSHA1().sign(private_key, str_to_sign);
		System.out.println("signature: " + signature);
		
		String authorization = "UCloud" + " " + public_key + ":" + signature;
		
		System.out.println("authorization: " + authorization);
		
		String uri = "http://" + bucket + ".ufile.ucloud.cn/" + key;
		HttpPut putMethod = new HttpPut(uri);
		System.out.println("available1>>"+inputStream.available());
		
		putMethod.setHeader("Content-Type", content_type);
		putMethod.setHeader("Authorization", authorization);
		putMethod.setHeader("X-UCloud-Hello", "hello");
		putMethod.setHeader("X-UCloud-World", "world");
		
		long length = inputStream.available();
//		while(inputStream.read() != -1){
//			length ++;
//		}
		System.out.println("stream length>>"+length);
		System.out.println("available>>"+inputStream.available());
//		inputStream = new FileInputStream(file);
//		InputStreamEntity entity = new InputStreamEntity(new FileInputStream(file), file.length());
		InputStreamEntity entity = new InputStreamEntity(inputStream, length);
		
		putMethod.setEntity(entity);
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(putMethod);
		HttpEntity resEntity = response.getEntity();
		
		System.out.println(response.getStatusLine());
		if (resEntity != null) {
			System.out.println("Response content length: " + resEntity.getContentLength());
			InputStream is = resEntity.getContent(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String s = "";
			while ((s = reader.readLine()) != null) {
				System.out.println(s);
			}
		}
		client.getConnectionManager().shutdown();
	}
	
	public String getFileContentType(String suffix){
		return fileContentTypeMap.get(suffix);
	}
	
	private UploadFileTool(){
		//文件上传http的contentType
		fileContentTypeMap = new HashMap();
		fileContentTypeMap.put("rar", "application/x-zip-compressed");
		fileContentTypeMap.put("zip", "application/x-zip-compressed");
		fileContentTypeMap.put("apk", "application/vnd.android.package-archive");
		fileContentTypeMap.put("ai", "application/postscript");
		fileContentTypeMap.put("aif", "audio/x-aiff");
		fileContentTypeMap.put("aifc", "audio/x-aiff");                        
		fileContentTypeMap.put("aiff", "audio/x-aiff");
		fileContentTypeMap.put("asc", "text/plain");
		fileContentTypeMap.put("au", "audio/basic");
		fileContentTypeMap.put("avi", "video/x-msvideo");
		fileContentTypeMap.put("bcpio", "application/x-bcpio");                
		fileContentTypeMap.put("bin", "application/octet-stream");            
		fileContentTypeMap.put("bmp", "image/bmp");                          
		fileContentTypeMap.put("cdf", "application/x-netcdf");                
		fileContentTypeMap.put("class", "application/octet-stream");            
		fileContentTypeMap.put("cpio", "application/x-cpio");                  
		fileContentTypeMap.put("cpt", "application/mac-compactpro");          
		fileContentTypeMap.put("csh", "application/x-csh");                  
		fileContentTypeMap.put("css", "text/css");
		fileContentTypeMap.put("dcr", "application/x-director");              
		fileContentTypeMap.put("dir", "application/x-director");
		fileContentTypeMap.put("djv", "image/vnd.djvu");
		fileContentTypeMap.put("djvu", "image/vnd.djvu");
		fileContentTypeMap.put("dll", "application/octet-stream");
		fileContentTypeMap.put("dms", "application/octet-stream");
		fileContentTypeMap.put("doc", "application/msword");      
		fileContentTypeMap.put("dvi", "application/x-dvi");      
		fileContentTypeMap.put("dxr", "application/x-director");  
		fileContentTypeMap.put("eps", "application/postscript");  
		fileContentTypeMap.put("etx", "text/x-setext");
		fileContentTypeMap.put("exe", "application/octet-stream");
		fileContentTypeMap.put("ez", "application/andrew-inset");
		fileContentTypeMap.put("gif", "image/gif");    
		fileContentTypeMap.put("gtar", "application/x-gtar");      
		fileContentTypeMap.put("hdf", "application/x-hdf");      
		fileContentTypeMap.put("hqx", "application/mac-binhex40");
		fileContentTypeMap.put("htm", "text/html");    
		fileContentTypeMap.put("html", "text/html");    
		fileContentTypeMap.put("ice", "x-conference/x-cooltalk");
		fileContentTypeMap.put("ief", "image/ief");    
		fileContentTypeMap.put("iges", "model/iges");    
		fileContentTypeMap.put("igs", "model/iges");    
		fileContentTypeMap.put("jpe", "image/jpeg");    
		fileContentTypeMap.put("jpeg", "image/jpeg");    
		fileContentTypeMap.put("jpg", "image/jpeg");    
		fileContentTypeMap.put("js", "application/x-javascript");
		fileContentTypeMap.put("kar", "audio/midi");    
		fileContentTypeMap.put("latex", "application/x-latex");    
		fileContentTypeMap.put("lha", "application/octet-stream");
		fileContentTypeMap.put("lzh", "application/octet-stream");
		fileContentTypeMap.put("m3u", "audio/x-mpegurl");        
		fileContentTypeMap.put("man", "application/x-troff-man");
		fileContentTypeMap.put("me", "application/x-troff-me");  
		fileContentTypeMap.put("mesh", "model/mesh");    
		fileContentTypeMap.put("mid", "audio/midi");    
		fileContentTypeMap.put("midi", "audio/midi");    
		fileContentTypeMap.put("mif", "application/vnd.mif");    
		fileContentTypeMap.put("mov", "video/quicktime");        
		fileContentTypeMap.put("movie", "video/x-sgi-movie");      
		fileContentTypeMap.put("mp2", "audio/mpeg");    
		fileContentTypeMap.put("mp3", "audio/mpeg");    
		fileContentTypeMap.put("mpe", "video/mpeg");    
		fileContentTypeMap.put("mpeg", "video/mpeg");    
		fileContentTypeMap.put("mpg", "video/mpeg");    
		fileContentTypeMap.put("mpga", "audio/mpeg");    
		fileContentTypeMap.put("ms", "application/x-troff-ms");  
		fileContentTypeMap.put("msh", "model/mesh");    
		fileContentTypeMap.put("mxu", "video/vnd.mpegurl");      
		fileContentTypeMap.put("nc", "application/x-netcdf");    
		fileContentTypeMap.put("oda", "application/oda");        
		fileContentTypeMap.put("pbm", "image/x-portable-bitmap");
		fileContentTypeMap.put("pdb", "chemical/x-pdb");
		fileContentTypeMap.put("pdf", "application/pdf");        
		fileContentTypeMap.put("pgm", "image/x-portable-graymap");
		fileContentTypeMap.put("pgn", "application/x-chess-pgn");
		fileContentTypeMap.put("png", "image/png");    
		fileContentTypeMap.put("pnm", "image/x-portable-anymap");
		fileContentTypeMap.put("ppm", "image/x-portable-pixmap");
		fileContentTypeMap.put("ppt", "application/vnd.ms-powerpoint");
		fileContentTypeMap.put("ps", "application/postscript");
		fileContentTypeMap.put("qt", "video/quicktime");
		fileContentTypeMap.put("ra", "audio/x-realaudio");
		fileContentTypeMap.put("ram", "audio/x-pn-realaudio");
		fileContentTypeMap.put("ras", "image/x-cmu-raster");
		fileContentTypeMap.put("rgb", "image/x-rgb");  
		fileContentTypeMap.put("rm", "audio/x-pn-realaudio");    
		fileContentTypeMap.put("roff", "application/x-troff");    
		fileContentTypeMap.put("rpm", "audio/x-pn-realaudio-plugin");
		fileContentTypeMap.put("rtf", "text/rtf");      
		fileContentTypeMap.put("rtx", "text/richtext");
		fileContentTypeMap.put("sgm", "text/sgml");    
		fileContentTypeMap.put("sgml", "text/sgml");    
		fileContentTypeMap.put("sh", "application/x-sh");        
		fileContentTypeMap.put("shar", "application/x-shar");      
		fileContentTypeMap.put("silo", "model/mesh");    
		fileContentTypeMap.put("sit", "application/x-stuffit");  
		fileContentTypeMap.put("skd", "application/x-koan");      
		fileContentTypeMap.put("skm", "application/x-koan");      
		fileContentTypeMap.put("skp", "application/x-koan");      
		fileContentTypeMap.put("skt", "application/x-koan");      
		fileContentTypeMap.put("smi", "application/smil");        
		fileContentTypeMap.put("smil", "application/smil");        
		fileContentTypeMap.put("snd", "audio/basic");  
		fileContentTypeMap.put("so", "application/octet-stream");
		fileContentTypeMap.put("spl", "application/x-futuresplash");
		fileContentTypeMap.put("src", "application/x-wais-source");
		fileContentTypeMap.put("sv4cpio", "application/x-sv4cpio");  
		fileContentTypeMap.put("sv4crc", "application/x-sv4crc");    
		fileContentTypeMap.put("swf", "application/x-shockwave-flash");
		fileContentTypeMap.put("t", "application/x-troff");    
		fileContentTypeMap.put("tar", "application/x-tar");      
		fileContentTypeMap.put("tcl", "application/x-tcl");      
		fileContentTypeMap.put("tex", "application/x-tex");      
		fileContentTypeMap.put("texi", "application/x-texinfo");  
		fileContentTypeMap.put("texinfo", "application/x-texinfo");  
		fileContentTypeMap.put("tif", "image/tiff");    
		fileContentTypeMap.put("tiff", "image/tiff");    
		fileContentTypeMap.put("tr", "application/x-troff");    
		fileContentTypeMap.put("tsv", "text/tab-separated-values");
		fileContentTypeMap.put("txt", "text/plain");    
		fileContentTypeMap.put("ustar", "application/x-ustar");    
		fileContentTypeMap.put("vcd ", "application/x-cdlink");    
		fileContentTypeMap.put("vrml", "model/vrml");    
		fileContentTypeMap.put("wav", "audio/x-wav");  
		fileContentTypeMap.put("wbmp", "image/vnd.wap.wbmp");      
		fileContentTypeMap.put("wbxml", "application/vnd.wap.wbxml");
		fileContentTypeMap.put("wml", "text/vnd.wap.wml");        
		fileContentTypeMap.put("wmlc", "application/vnd.wap.wmlc");
		fileContentTypeMap.put("wmls", "text/vnd.wap.wmlscript");  
		fileContentTypeMap.put("wmlsc", "application/vnd.wap.wmlscriptc");
		fileContentTypeMap.put("wrl", "model/vrml");    
		fileContentTypeMap.put("xbm", "image/x-xbitmap");        
		fileContentTypeMap.put("xht", "application/xhtml+xml");  
		fileContentTypeMap.put("xhtml", "application/xhtml+xml");  
		fileContentTypeMap.put("xls", "application/vnd.ms-excel");
		fileContentTypeMap.put("xml", "text/xml");      
		fileContentTypeMap.put("xpm", "image/x-xpixmap");        
		fileContentTypeMap.put("xsl", "text/xml");
		fileContentTypeMap.put("xwd", "image/x-xwindowdump");    
		fileContentTypeMap.put("xyz", "chemical/x-xyz");
		fileContentTypeMap.put("zip", "application/zip");
	}

	public BaseResultInfo uploadLicenImg(List<Map<String, Object>> fileList, String filePath) {
		BaseResultInfo result = new BaseResultInfo();
		result.setMsg("");
		result.setSuccess(false);
		HashMap<String,Object> map = new HashMap<String, Object>();
		result.setSuccess(false);
		try {
			UploadFileTool uploadFileTool=UploadFileTool.getInstance();
			filePath = filePath + System.currentTimeMillis();
			String[] fileUrl = new String[fileList.size()];
			for(int i = 0; fileList != null && i < fileList.size(); i++){
				InputStream flie = (InputStream)fileList.get(i).get("stream");
				String filename = (String)fileList.get(i).get("filename");
				fileUrl[i] = uploadFileTool.upLoadWithUFilePut(flie, filePath , filename);
			}
			map.put("url",fileUrl[0]);
			result.setExtInfo(map);
			String url=fileUrl[0];
			HashMap<String,Object> map1 = new HashMap<>();
			map1.put("url",url);
			result.setExtInfo(map1);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
