package com.ddzhuan.manage.service;

import com.ddzhuan.manage.model.YouPengOrder;

public interface YPOrderService {
	
	//根据友朋的订单号和支付流水号查询订单
	public YouPengOrder queryOrderByYPreceiptnoAndTradeno(String ypreceiptno,String tradeno);

	//更新订单状态
	public int renewOrder(YouPengOrder YouPengOrder);
	
	//插入订单
	public int addOrder(YouPengOrder YouPengOrder);
	
	
}
