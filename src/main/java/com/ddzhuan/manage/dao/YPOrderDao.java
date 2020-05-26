package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.YouPengOrder;
import org.apache.ibatis.annotations.Param;

public interface YPOrderDao {
	
	//插入订单
	public int insertOrder(YouPengOrder YouPengOrder);
	
	//更新订单状态
	public int updateOrder(YouPengOrder YouPengOrder);
	
	//根据友朋的订单号和支付流水号查询订单
	public YouPengOrder selectOrderByYPreceiptnoAndTradeno(@Param("ypreceiptno")String ypreceiptno,@Param("tradeno")String tradeno);
}
