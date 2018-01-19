package cn.wangjiannan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.common.shiro.ShiroUser;
import cn.wangjiannan.mapper.ResourceMapper;
import cn.wangjiannan.mapper.RoleMapper;
import cn.wangjiannan.mapper.UserRoleMapper;
import cn.wangjiannan.model.Resource;
import cn.wangjiannan.model.vo.TreeVo;
import cn.wangjiannan.service.ResourceService;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
	private static final int RESOURCE_MENU = 0; // 菜单

	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<TreeVo> selectTree(ShiroUser shiroUser) {
		List<TreeVo> trees = new ArrayList<TreeVo>();
		// shiro中缓存的用户角色
		Set<String> roles = shiroUser.getRoles();
		if (roles == null) {
			return trees;
		}
		// 如果有超级管理员权限
		if (roles.contains("admin")) {
			List<Resource> resourceList = this.selectByType(RESOURCE_MENU);
			if (resourceList == null) {
				return trees;
			}
			for (Resource resource : resourceList) {
				TreeVo tree = new TreeVo();
				tree.setId(resource.getId());
				tree.setPid(resource.getPid());
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				tree.setAttributes(resource.getUrl());
				tree.setOpenMode(resource.getOpenMode());
				tree.setState(resource.getOpened());
				trees.add(tree);
			}
			return trees;
		}
		// 普通用户
		List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
		if (roleIdList == null) {
			return trees;
		}
		List<Resource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
		if (resourceLists == null) {
			return trees;
		}
		for (Resource resource : resourceLists) {
			TreeVo tree = new TreeVo();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setOpenMode(resource.getOpenMode());
			tree.setState(resource.getOpened());
			trees.add(tree);
		}
		return trees;
	}

	public List<Resource> selectByType(Integer type) {
		EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
		Resource resource = new Resource();
		wrapper.setEntity(resource);
		wrapper.addFilter("resource_type = {0}", type);
		wrapper.orderBy("seq");
		return resourceMapper.selectList(wrapper);
	}

}
