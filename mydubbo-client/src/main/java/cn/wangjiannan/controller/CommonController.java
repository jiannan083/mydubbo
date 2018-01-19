package cn.wangjiannan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cn.wangjiannan.common.shiro.captcha.DreamCaptcha;

@Controller
public class CommonController {
	@Autowired
	private DreamCaptcha dreamCaptcha;

	/**
	 * 图形验证码
	 */
	@GetMapping("/captcha.jpg")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		dreamCaptcha.generate(request, response);
	}

}
