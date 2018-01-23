package cn.wangjiannan.api.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.api.model.Organization;
import cn.wangjiannan.api.model.vo.TreeVo;

public interface OrganizationService extends IService<Organization> {
	List<TreeVo> selectTree();

	List<Organization> selectTreeGrid();
}
