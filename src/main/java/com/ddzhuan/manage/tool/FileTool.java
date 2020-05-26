package com.ddzhuan.manage.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileTool {
	
	public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            //System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
            return true;
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            //System.out.println("创建目录" + destDirName + "成功！");  
            return true;  
        } else {  
            //System.out.println("创建目录" + destDirName + "失败！");  
            return false;  
        }  
    }
	
	
	public static boolean createFile(InputStream inputStream, File file) {
		String destFileName = file.getAbsolutePath();
		try{
			if(file.exists()) {
	            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");  
	            return false;  
	        }
			//判断目标文件所在的目录是否存在  
	        if(!file.getParentFile().exists()) {
	            //如果目标文件所在的目录不存在，则创建父目录  
	            System.out.println("目标文件所在目录不存在，准备创建它！");  
	            if(!file.getParentFile().mkdirs()) {  
	                System.out.println("创建目标文件所在目录失败！");  
	                return false;  
	            }  
	        }  
			FileOutputStream streamw = new FileOutputStream(file);
			int b = -1;
			while((b = inputStream.read()) != -1){
				streamw.write(b);
			}
			inputStream.close();
			streamw.close();
		}catch(Exception ex){
			return false;
		}
        return true;
    }  
	
}
