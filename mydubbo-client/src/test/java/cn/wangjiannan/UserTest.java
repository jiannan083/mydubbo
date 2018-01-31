package cn.wangjiannan;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.api.model.User;
import cn.wangjiannan.api.model.vo.UserVo;
import cn.wangjiannan.api.service.RoleService;
import cn.wangjiannan.api.service.UserService;
import cn.wangjiannan.base.BaseTest;

public class UserTest extends BaseTest {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	// @Test
	// public void testUser() {
	// UserVo userVo = new UserVo();
	// userVo.setLoginName("admin");
	// // userVo.setId(1L);
	// List<User> list = userService.selectByLoginName(userVo);
	// logger.info("-----" + list.get(0).toString());
	// }
	//
	// @Test
	// public void testUserPage() {
	// // PageVo<User> pageVo = new PageVo<>(1, 10, "id", "asc");
	// // Map<String, Object> condition = new HashMap<String, Object>();
	// // pageVo.setCondition(condition);
	// // userService.selectUserPage(pageVo);
	// // System.out.println("-----------");
	// }

	@Test
	public void testUser1() {
		UserVo user = new UserVo();
		user.setLoginName("admin");
		// userVo.setId(1L);
		List<User> list = userService.selectByLoginName(user);
		logger.info("-----" + list.get(0).toString());

		logger.info("-----" + roleService.selectResourceMapByUserId(1L));
	}
}
