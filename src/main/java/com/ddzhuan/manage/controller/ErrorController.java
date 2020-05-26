package com.ddzhuan.manage.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 错误页面
 *
 * @author Acer
 */
@Controller
@RequestMapping("/errorpage/")
public class ErrorController extends BaseController {

	private static final long serialVersionUID = 7106682878751596400L;

	@RequestMapping(value = "404", method = RequestMethod.GET)
	public String get404page(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "errorpage/404";
	}

	@RequestMapping(value = "405", method = RequestMethod.GET)
	public String get405page(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "errorpage/405";
	}

	@RequestMapping(value = "500", method = RequestMethod.GET)
	public String get500page(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "errorpage/500";
	}

	@RequestMapping(value = "loginError", method = RequestMethod.GET)
	public String loginError(@RequestParam(required = false, defaultValue = "") String msg, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if(StringUtils.isEmpty(msg)){
			msg = "服务器连接失败";
		}
		model.addAttribute("msg", msg);
		return "errorpage/error";
	}

}
