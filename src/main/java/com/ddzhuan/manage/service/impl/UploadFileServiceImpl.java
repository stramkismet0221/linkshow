package com.ddzhuan.manage.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ddzhuan.manage.service.UploadFileService;
import com.ddzhuan.manage.tool.UploadFileTool;

@Service
public class UploadFileServiceImpl implements UploadFileService{

	private Logger log = Logger.getLogger(UploadFileServiceImpl.class);
	
	@Override
	public String[] uploadMiaoTaskImageFile(List<Map<String, Object>> fileList, String dir) {
		if(fileList == null || dir == null){
			return null;
		}
		UploadFileTool ufTool = UploadFileTool.getInstance();
		String[] fileUrl = new String[fileList.size()];
		try{
			String filePath = "miaotaskimg/" + dir ;
			for(int i = 0; fileList != null && i < fileList.size(); i++){
				InputStream flie = (InputStream)fileList.get(i).get("stream");
				String filename = (String)fileList.get(i).get("filename");
				fileUrl[i] = ufTool.upLoadWithUFilePut(flie, filePath , filename);
			}
		}catch(Exception ex){
			log.error(ex);
		}
		return fileUrl;
	}

	@Override
	public String[] uploadMiaoTaskApkFile(List<Map<String, Object>> fileList, String dir) {
		if(fileList == null || dir == null){
			return null;
		}
		UploadFileTool ufTool = UploadFileTool.getInstance();
		String[] fileUrl = new String[fileList.size()];
		try{
			String filePath = "miaotaskapk/" + dir ;
			for(int i = 0; fileList != null && i < fileList.size(); i++){
				InputStream flie = (InputStream)fileList.get(i).get("stream");
				String filename = (String)fileList.get(i).get("filename");
				fileUrl[i] = ufTool.upLoadWithUFilePut(flie, filePath , filename);
			}
		}catch(Exception ex){
			log.error(ex);
		}
		return fileUrl;
	}

	@Override
	public String[] uploadMiaoTaskRecoveryAdjFile(List<Map<String, Object>> fileList, String dir) {
		if(fileList == null || dir == null){
			return null;
		}
		UploadFileTool ufTool = UploadFileTool.getInstance();
		String[] fileUrl = new String[fileList.size()];
		try{
			String filePath = "recorveyadj/" + dir ;
			for(int i = 0; fileList != null && i < fileList.size(); i++){
				InputStream flie = (InputStream)fileList.get(i).get("stream");
				String filename = (String)fileList.get(i).get("filename");
				fileUrl[i] = ufTool.upLoadWithUFilePut(flie, filePath , filename);
			}
		}catch(Exception ex){
			log.error(ex);
		}
		return fileUrl;
	}
	
	@Override
	public boolean deleteUFile(List<String> uFileCdnUrls){
		boolean flg = false;
		try{
			UploadFileTool ufTool = UploadFileTool.getInstance();
			for(int i = 0; uFileCdnUrls != null && i < uFileCdnUrls.size(); i++){
				ufTool.deleteWithUFileDelete(uFileCdnUrls.get(i));
			}
		}catch(Exception ex){
			
		}
		return flg;
	}

}
