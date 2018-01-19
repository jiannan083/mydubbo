package cn.wangjiannan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.model.UserRole;

public interface UserRoleService extends IService<UserRole> {
	List<UserRole> selectUserRoles(UserRole userRole);
}
