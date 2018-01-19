package cn.wangjiannan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.common.base.BaseController;
import cn.wangjiannan.common.shiro.ShiroUser;
import cn.wangjiannan.service.ResourceService;

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
	@PostMapping("/tree")
	@ResponseBody
	public Object tree() {
		ShiroUser shiroUser = getShiroUser();
		return resourceService.selectTree(shiroUser);
	}
}
