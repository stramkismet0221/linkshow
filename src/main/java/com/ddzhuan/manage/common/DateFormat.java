package com.ddzhuan.manage.common;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static Date Sysdate(){
		Date date = new Date();
		return date;
	}
	/**
	 * 字符串转换成时间类型
	 * @param date
	 * @return
	 */
	public static Date Stringdate(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d=null;
		try {
			 d= sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	

}
