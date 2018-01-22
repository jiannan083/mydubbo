package cn.wangjiannan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.model.vo.TreeVo;
import cn.wangjiannan.mydubbo.model.Organization;

public interface OrganizationService extends IService<Organization> {
	List<TreeVo> selectTree();

	List<Organization> selectTreeGrid();
}
