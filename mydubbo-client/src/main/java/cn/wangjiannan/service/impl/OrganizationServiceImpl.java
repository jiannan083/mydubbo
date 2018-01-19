package cn.wangjiannan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.OrganizationMapper;
import cn.wangjiannan.model.Organization;
import cn.wangjiannan.model.vo.TreeVo;
import cn.wangjiannan.service.OrganizationService;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
	@Autowired
	private OrganizationMapper organizationMapper;

	@Override
	public List<TreeVo> selectTree() {
		List<Organization> organizationList = selectTreeGrid();

		List<TreeVo> trees = new ArrayList<TreeVo>();
		if (organizationList != null) {
			for (Organization organization : organizationList) {
				TreeVo tree = new TreeVo();
				tree.setId(organization.getId());
				tree.setText(organization.getName());
				tree.setIconCls(organization.getIcon());
				tree.setPid(organization.getPid());
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public List<Organization> selectTreeGrid() {
		EntityWrapper<Organization> wrapper = new EntityWrapper<Organization>();
		wrapper.orderBy("seq");
		return organizationMapper.selectList(wrapper);
	}
}
