package com.ddzhuan.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddzhuan.manage.dao.YPOrderDao;
import com.ddzhuan.manage.model.YouPengOrder;
import com.ddzhuan.manage.service.YPOrderService;

@Service
public class YPOrderServiceImpl implements YPOrderService {

	@Autowired
	protected  YPOrderDao yPOrderDao;

	@Override
	public YouPengOrder queryOrderByYPreceiptnoAndTradeno(String ypreceiptno, String tradeno) {
		return yPOrderDao.selectOrderByYPreceiptnoAndTradeno(ypreceiptno, tradeno);
	}

	@Override
	public int renewOrder(YouPengOrder YouPengOrder) {
		return yPOrderDao.updateOrder(YouPengOrder);
	}

	@Override
	public int addOrder(YouPengOrder YouPengOrder) {
		return yPOrderDao.insertOrder(YouPengOrder);
	}
}
