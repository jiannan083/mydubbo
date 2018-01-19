package cn.wangjiannan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.mapper.UserRoleMapper;
import cn.wangjiannan.model.User;
import cn.wangjiannan.model.UserRole;
import cn.wangjiannan.model.vo.PageVo;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public List<User> selectByLoginName(UserVo userVo) {
		User user = new User();
		user.setLoginName(userVo.getLoginName());
		// EntityWrapper实体包装器、封装器
		EntityWrapper<User> wrapper = new EntityWrapper<User>(user);
		if (null != userVo.getId()) {
			wrapper.where("id != {0}", userVo.getId());
		}
		return this.selectList(wrapper);
	}

	@Override
	public void selectDataGrid(PageVo<Map<String, Object>> pageVo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageVo.getNowpage(), pageVo.getSize());
		page.setOrderByField(pageVo.getSort());
		page.setAsc(pageVo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = userMapper.selectUserPage(page, pageVo.getCondition());
		pageVo.setRows(list);
		pageVo.setTotal(page.getTotal());
	}

	@Override
	public void insertByUserVo(UserVo userVo) {
		// User user = new User();
		// try {
		// // BeanUtils.copyProperties(Object dest目的对象, Object orig数据源);
		// BeanUtils.copyProperties(user, userVo);
		// // PropertyUtils.copyProperties(Object dest目的对象, Object orig数据源);
		// // PropertyUtils.copyProperties(user, userVo);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		userVo.setCreateTime(new Date());
		this.insert(userVo);

		Long id = userVo.getId();
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	// @Override
	// public void selectUserPage(PageVo<User> pageVo) {
	// Page<User> page = new Page<>(pageVo.getNowpage(), pageVo.getPagesize());
	// EntityWrapper<User> wrapper = new EntityWrapper<User>();
	// wrapper.orderBy(pageVo.getSort(), pageVo.getOrder().equalsIgnoreCase("ASC"));
	// selectPage(page, wrapper);
	// pageVo.setRows(page.getRecords());
	// pageVo.setTotal(page.getTotal());
	//
	// // Page<User> page = new Page<>(pageVo.getNowpage(), pageVo.getPagesize());
	// // page.setOrderByField(pageVo.getSort());
	// // page.setAsc(pageVo.getOrder().equalsIgnoreCase("asc"));
	// // System.out.println("---------" + page.toString());
	// // userMapper.selectUserPage(page, pageVo.getCondition());
	// // pageVo.setRows(page.getRecords());
	// // pageVo.setTotal(page.getTotal());
	// }

}
