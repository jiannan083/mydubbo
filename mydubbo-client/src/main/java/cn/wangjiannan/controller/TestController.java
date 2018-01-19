package cn.wangjiannan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.common.base.BaseController;
import cn.wangjiannan.manager.ActiveMQProducerManager;
import cn.wangjiannan.manager.InvokeBaiduManager;
import cn.wangjiannan.model.vo.UserVo;

/**
 * 测试
 * 
 * @author wangjiannan
 *
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	// @Autowired
	// private UserService userService;
	@Autowired
	private ActiveMQProducerManager activeMQProducerManager;
	@Autowired
	private InvokeBaiduManager invokeBaiduManager;

	@RequestMapping("/test1")
	@ResponseBody
	public void test1() {
		// PageVo<User> pageVo = new PageVo<>(1, 10);
		// Map<String, Object> condition = new HashMap<String, Object>();
		// pageVo.setCondition(condition);
		// userService.selectUserPage(pageVo);
		// logger.info("-------------------------------");
		// logger.info("-------------dsfdsafa------------------");
		// logger.info("-------------dsfdsafadf`ghjukiop------------------");
		// User user = userService.selectById(1L);
		// System.out.println("---------" + user.getName());
		// System.out.println("----------------");
		// UserVo userVo = new UserVo();
		// userVo.setLoginName("admin");
		// // userVo.setId(1L);
		// List<User> list = userService.selectByLoginName(userVo);
		// logger.info("-----" + list.get(0).toString());

	}

	@RequestMapping("/testValidated")
	@ResponseBody
	public Object testValidated(@Valid UserVo userVo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError error : allErrors) {
				System.out.println("我是验证错误:" + error.getDefaultMessage());
				return error.getDefaultMessage();
			}
		}
		return null;
	}

	@RequestMapping("/testQueue")
	public void testQueue(String message) {
		activeMQProducerManager.testQueueTemplate(message);
	}

	@RequestMapping("/testTopic")
	public void testTopic(String message) {
		activeMQProducerManager.testTopicTemplate(message);

	}

	@RequestMapping("/testInvokeBaidu")
	public void testInvokeBaidu(String latLng) {
		invokeBaiduManager.getPositionByLatLng(latLng);
	}

}
