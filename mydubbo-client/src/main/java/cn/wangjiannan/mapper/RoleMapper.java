package cn.wangjiannan.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.wangjiannan.mydubbo.model.Resource;
import cn.wangjiannan.mydubbo.model.Role;

public interface RoleMapper extends BaseMapper<Role> {

	List<Map<String, Object>> selectResourceListByRoleId(Long id);

	List<Resource> selectResourceListByRoleIdList(List<Long> list);

}
