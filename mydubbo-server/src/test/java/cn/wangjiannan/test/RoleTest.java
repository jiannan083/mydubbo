package cn.wangjiannan.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.api.model.UserRole;
import cn.wangjiannan.api.service.UserRoleService;
import cn.wangjiannan.base.BaseTest;
import cn.wangjiannan.server.mapper.UserRoleMapper;

public class RoleTest extends BaseTest {
	// @Autowired
	// private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserRoleService userRoleService;
	// @Autowired
	// private RoleResourceMapper roleResourceMapper;

	@Test
	public void testRole1() {
		List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(1L);
		logger.info("-----{}", roleIdList);
		UserRole userRole = new UserRole();
		userRole.setUserId(1L);
		List<UserRole> userRoleList = userRoleService.selectUserRoles(userRole);
		logger.info("-----{}", userRoleList);
	}
}
