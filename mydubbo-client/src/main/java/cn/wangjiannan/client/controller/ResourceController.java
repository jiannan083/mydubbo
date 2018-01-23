package cn.wangjiannan.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wangjiannan.api.service.ResourceService;
import cn.wangjiannan.client.common.base.BaseController;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 菜单树
	 *
	 * @return
	 */
	// @PostMapping("/tree")
	// @ResponseBody
	// public Object tree() {
	// ShiroUser shiroUser = getShiroUser();
	// return resourceService.selectTree(shiroUser);
	// }
}
