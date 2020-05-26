package com.ddzhuan.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//抽奖
@Controller
@RequestMapping("/aliPay")
public class AliPayReturnController {

	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public void getAliPayReturn(HttpServletRequest request, HttpServletResponse response, Model model) {
		String[] uri = request.getRequestURI().split("/");
		try {
			String uid = uri[uri.length - 1];
			System.out.println(String.format("【%s】完成支付，跳转", uid));
			response.sendRedirect("../index?uid=" + uid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
