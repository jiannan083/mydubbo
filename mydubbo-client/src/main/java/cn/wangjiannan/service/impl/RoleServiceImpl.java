package cn.wangjiannan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.common.util.StringUtils;
import cn.wangjiannan.mapper.RoleMapper;
import cn.wangjiannan.mapper.UserRoleMapper;
import cn.wangjiannan.model.Role;
import cn.wangjiannan.model.vo.TreeVo;
import cn.wangjiannan.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	// @Autowired
	// private RoleResourceMapper roleResourceMapper;

	public List<Role> selectAll() {
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.orderBy("seq");
		return roleMapper.selectList(wrapper);
	}

	@Override
	public Object selectTree() {
		List<TreeVo> trees = new ArrayList<TreeVo>();
		List<Role> roles = this.selectAll();
		for (Role role : roles) {
			TreeVo tree = new TreeVo();
			tree.setId(role.getId());
			tree.setText(role.getName());

			trees.add(tree);
		}
		return trees;
	}

	@Override
	public Map<String, Set<String>> selectResourceMapByUserId(Long userId) {
		Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
		List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(userId);
		Set<String> urlSet = new HashSet<String>();
		Set<String> roles = new HashSet<String>();
		for (Long roleId : roleIdList) {
			List<Map<String, Object>> resourceList = roleMapper.selectResourceListByRoleId(roleId);
			if (resourceList != null) {
				for (Map<String, Object> map : resourceList) {
					String url = String.valueOf(map.get("url"));
					if (StringUtils.isNotBlank(url)) {
						urlSet.add(url);
					}
				}
			}
			//
			Role role = roleMapper.selectById(roleId);
			if (role != null) {
				roles.add(role.getName());
			}
		}
		resourceMap.put("urls", urlSet);
		resourceMap.put("roles", roles);
		return resourceMap;
	}

}
