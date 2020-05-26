package com.ddzhuan.manage.schedule;

import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddzhuan.manage.service.BaseService;

public class BaseTask  extends TimerTask{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	
	@Override
	public void run() {
	
	}
}
