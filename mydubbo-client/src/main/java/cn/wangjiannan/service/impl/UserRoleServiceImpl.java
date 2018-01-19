package cn.wangjiannan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.UserRoleMapper;
import cn.wangjiannan.model.UserRole;
import cn.wangjiannan.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

	@Override
	public List<UserRole> selectUserRoles(UserRole userRole) {
		EntityWrapper<UserRole> wrapper = new EntityWrapper<UserRole>(userRole);
		return this.selectList(wrapper);
	}

}
