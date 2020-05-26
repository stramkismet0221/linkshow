package com.ddzhuan.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.service.SysInfoService;

@Controller
@RequestMapping("/")
public class SysInfoServiceController extends BaseController {

	@Autowired
	protected SysInfoService sysInfoService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6216846826432638481L;
	
}
