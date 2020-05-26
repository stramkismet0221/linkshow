package com.ddzhuan.manage.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.tool.CodeTool;
import com.ddzhuan.manage.tool.IPTool;

@Controller
@RequestMapping("/t/")
public class ToolServiceController extends BaseController {
	private static final long serialVersionUID = 5633331013449452994L;
	@Autowired
	protected SysParam _config;
	@Autowired
	protected Producer captchaProducer;
	@RequestMapping(value = "pimgcode", method = RequestMethod.OPTIONS)
	public void getImageCodeForPhone(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.addHeader("Access-Control-Allow-Origin", "*");
	}
	/**
	 * 获取图片验证码
	 * 
	 * @param request
	 * @param response , method = RequestMethod.POST
	 * @return
	 */
	@RequestMapping(value = "pimgcode")
	public String getImageCodeForPhone(HttpServletRequest request,HttpServletResponse response) {
		String random = request.getParameter("random");
		String mobiles = request.getParameter("mobiles");
		System.out.println("手机号："+mobiles);
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = captchaProducer.createText();
		cache.put("smsCache", mobiles+"_pimgcode", capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		request.getSession().setAttribute("pigcode", capText);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}
	/**
	 * 获取手机验证码
	 * @param mobiles
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "phcode", method = RequestMethod.POST)
	@ResponseBody
	public BaseResultInfo tSmsPhoneCode(
			@RequestParam(required = true)String mobiles, 
			HttpServletRequest request,HttpServletResponse response) {
		BaseResultInfo result = new BaseResultInfo();
		result.setSuccess(false);
		if (mobiles == null || "".equals(mobiles.trim())) {
			result.setMsg("手机号码不能为空！");
			return result;
		}
		result.setMsg("验证码获取失败");
		try {
			String vsIp = IPTool.getIPAddr(request);
			String phcode = CodeTool.getRandomCode();
			if(cache.get("smsCache", mobiles)!=null){
				phcode = String.valueOf(cache.get("smsCache", mobiles));
			}
			result = toolService.sendSms(_config, mobiles, vsIp, String.format("尊敬的会员，您申请的手机短信验证码：%s，为了保护账号安全，请勿向他人泄露。", phcode));
			result.setSuccess(true);
			log.info(">>>>>>>>>>>"+phcode);
			if (result.isSuccess()) {
				cache.put("smsCache", mobiles, phcode);
			} else {
				throw new Exception(result.getMsg());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

}
