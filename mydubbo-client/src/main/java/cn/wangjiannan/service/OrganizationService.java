package cn.wangjiannan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.model.Organization;
import cn.wangjiannan.model.vo.TreeVo;

public interface OrganizationService extends IService<Organization> {
	List<TreeVo> selectTree();

	List<Organization> selectTreeGrid();
}
