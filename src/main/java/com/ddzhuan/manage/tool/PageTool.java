package com.ddzhuan.manage.tool;

public class PageTool {

	private int page;
	
	private int pageSize;
	
	public PageTool(int page, int pageSize){
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public int getMYSQLLimitStart(){
		return page * pageSize - pageSize;
	}
	
	public int getMYSQLLimitSize(){
		return pageSize;
	}
	
	public int getOracleStart(){
		return page * pageSize - pageSize + 1;
	}
	
	public int getOracleEnd(){
		return page * pageSize;
	}
	
	public int getMaxPageNo(int count){
		if(count % pageSize > 0){
			return count / pageSize + 1;
		}else{
			return count / pageSize;
		}
		
	}
}
