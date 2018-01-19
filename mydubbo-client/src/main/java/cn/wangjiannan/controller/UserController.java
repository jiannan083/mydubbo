package cn.wangjiannan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.common.base.BaseController;
import cn.wangjiannan.common.shiro.PasswordHash;
import cn.wangjiannan.common.util.StringUtils;
import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.PageVo;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordHash passwordHash;

	/**
	 * 用户管理页
	 * 
	 * @author wangjiannan
	 * @date 2017年12月14日 下午3:50:02
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/user/user";
	}

	/**
	 * 用户管理列表
	 * 
	 * @author wangjiannan
	 * @date 2017年12月29日 上午10:09:15
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
		// page:第几页;rows:每页显示条数;sort:排序字段;order:asc/desc
		PageVo<Map<String, Object>> pageVo = new PageVo<>(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userVo.getName())) {
			condition.put("name", userVo.getName());
		}
		if (userVo.getOrganizationId() != null) {
			condition.put("organizationId", userVo.getOrganizationId());
		}
		if (userVo.getCreatedateStart() != null) {
			condition.put("startTime", userVo.getCreatedateStart());
		}
		if (userVo.getCreatedateEnd() != null) {
			condition.put("endTime", userVo.getCreatedateEnd());
		}
		pageVo.setCondition(condition);
		userService.selectDataGrid(pageVo);
		return pageVo;
	}

	/**
	 * 添加用户页
	 * 
	 * @author wangjiannan
	 * @date 2017年12月29日 上午10:09:35
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/user/userAdd";
	}

	/**
	 * 添加用户
	 * 
	 * @author wangjiannan
	 * @date 2017年12月29日 上午10:10:05
	 * @param userVo
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid UserVo userVo) {
		List<User> list = userService.selectByLoginName(userVo);
		if (list != null && !list.isEmpty()) {
			return renderError("登录名已存在!");
		}
		String salt = StringUtils.getUUId();
		String pwd = passwordHash.toHex(userVo.getPassword(), salt);
		userVo.setSalt(salt);
		userVo.setPassword(pwd);
		userService.insertByUserVo(userVo);
		return renderSuccess("添加成功");
	}

}
