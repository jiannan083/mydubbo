package cn.wangjiannan.service;

import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.model.Role;

public interface RoleService extends IService<Role> {
	Object selectTree();

	Map<String, Set<String>> selectResourceMapByUserId(Long userId);

}
