package com.ddzhuan.manage.tool;

public class InviteCodeTool {

	private static char[] digits = {'M', 'F', 'J', 'H', '6', 'K', 'I', 'Z', 'D', 'N', 
								'5', 'G', 'L', 'Y', 'P', '8', 'C', 'S', 'R', 'X', 
								'4', 'V', 'A', '7', 'U', '3', 'B', '0', 'O', '1', 
								'E', 'Q', '2', 'T', 'W', '9'};
	
	public static String generate(Long inputId){
		String rs = "";
		char[] invs = new char[6];
		int[] ii = {0, 2, 4, 1, 3, 5};
//		int[] ii = {5, 3, 1, 4, 2, 0};
		int clen = 0;
		while(clen < 6){
			if(inputId > 0 ){
				Long lrs = (inputId % 36);
				invs[ii[clen]] = digits[lrs.intValue()];
				rs += digits[lrs.intValue()];
				inputId = inputId / 36l;
			}else{
				invs[ii[clen]] = digits[0];
				rs += digits[0];
			}
			clen++;
//			System.out.println("rs="+rs);
		}
		rs = new String(invs);
//		System.out.println("rs="+rs);
		return rs;
	}
	
//	public static void main(String[] args){
//		InviteCodeTool.generate(2657l);
//	}
}
