package cn.wangjiannan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.common.base.BaseController;
import cn.wangjiannan.service.OrganizationService;

/**
 * 部门管理
 * 
 * @author wangjiannan
 * @date 2017年12月29日 上午10:27:03
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {
	@Autowired
	private OrganizationService organizationService;

	/**
	 * 部门资源树
	 *
	 * @return
	 */
	@PostMapping(value = "/tree")
	@ResponseBody
	public Object tree() {
		return organizationService.selectTree();
	}

}
