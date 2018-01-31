package cn.wangjiannan.api.service;

import java.util.List;

import cn.wangjiannan.api.model.ShiroUser;
import cn.wangjiannan.api.model.vo.TreeVo;

public interface ResourceService {
	List<TreeVo> selectTree(ShiroUser shiroUser);
}
