package com.ddzhuan.manage.common.enums;

public enum SalesReportEnum {

	CYCLE_TYPE_DAY(0),
	
	CYCLE_TYPE_WEEK(1),
	
	CYCLE_TYPE_MONTH(2),
	
	CYCLE_TYPE_QUARTER(3),
	
	CYCLE_TYPE_YEAR(4),
	
	CYCLE_TYPE_OVER_THE_PAST_WEEK(5),
	
	CYCLE_TYPE_OVER_THE_PAST_MONTH(6),
	
	CYCLE_TYPE_OVER_THE_PAST_QUARTER(7),
	
	CYCLE_TYPE_OVER_THE_PAST_YEAR (8),
	
	;
	
	private Integer value;
	
	private SalesReportEnum(Integer value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}

	