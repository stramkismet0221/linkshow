package com.ddzhuan.manage.service;

import java.util.List;
import java.util.Map;


public interface UploadFileService {
	
	/**
	 * 上传喵任务图示问卷
	 * @param fileList
	 * mapKey: filename
	 *      	stream
	 * @param miaoTask
	 * @return
	 */
	public String[] uploadMiaoTaskImageFile(List<Map<String, Object>> fileList, String dir);
	
	/**
	 * 上传喵任务APK文件
	 * @param fileList
	 * mapKey: filename
	 *      	stream
	 * @param miaoTask
	 * @return
	 */
	public String[] uploadMiaoTaskApkFile(List<Map<String, Object>> fileList, String dir);
	
	/**
	 * 上传喵任务回收用户上传的图片文件
	 * @param fileList
	 * map: filename
	 *      stream
	 * @param miaoTask
	 * @return
	 */
	public String[] uploadMiaoTaskRecoveryAdjFile(List<Map<String, Object>> fileList, String dir);

	/**
	 * 删除UFILE中的文件
	 * @param fileList
	 * mapKey: filename
	 *      	stream
	 * @param miaoTask
	 * @return
	 */
	public boolean deleteUFile(List<String> uFileCdnUrls);
	
}
