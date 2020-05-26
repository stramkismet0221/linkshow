package com.ddzhuan.manage.common.enums;

public enum AnswerStatEnum {

//	0：问卷，1：题目，2：选项，3：单元格
	STYPE_QUESTIONNAIRE(0),
	
	STYPE_QUESTION(1),
	
	STYPE_SELECTION(2),
	
	STYPE_CELL(3),
	
	;
	private Integer value;
	
	private AnswerStatEnum(Integer value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
