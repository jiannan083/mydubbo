package cn.wangjiannan.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.api.service.RoleService;
import cn.wangjiannan.client.common.base.BaseController;

/**
 * 权限管理
 * 
 * @author wangjiannan
 * @date 2017年12月29日 上午10:22:48
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;

	/**
	 * 权限树
	 * 
	 * @author wangjiannan
	 * @date 2017年12月29日 上午10:23:06
	 * @return
	 */
	@PostMapping("/tree")
	@ResponseBody
	public Object tree() {
		return roleService.selectTree();
	}
}
