package cn.wangjiannan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wangjiannan.service.TransService;

@Controller
@RequestMapping("/transController")
public class TransController {
	@Autowired
	private TransService transService;

	// localhost:8100/mydubbo_client/transController/testTrans
	@RequestMapping("/testTrans")
	public void testTrans() {
		transService.testTrans();
	}
}
