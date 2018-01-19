package cn.wangjiannan.service;

import java.util.List;

import cn.wangjiannan.common.shiro.ShiroUser;
import cn.wangjiannan.model.vo.TreeVo;

public interface ResourceService {
	List<TreeVo> selectTree(ShiroUser shiroUser);
}
