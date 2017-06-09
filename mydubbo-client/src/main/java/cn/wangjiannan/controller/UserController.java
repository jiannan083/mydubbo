package cn.wangjiannan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.server.model.ServerUser;
import cn.wangjiannan.server.service.ServerUserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	@Autowired
	private ServerUserService serverUserService;

	@RequestMapping("/login")
	public String index() {
		return "login";
	}

	@RequestMapping("/testDubbo")
	@ResponseBody
	public void testDubbo() {
		ServerUser serverUser = serverUserService.findUserById(1);
		System.out.println("-------------" + serverUser);
	}
}
