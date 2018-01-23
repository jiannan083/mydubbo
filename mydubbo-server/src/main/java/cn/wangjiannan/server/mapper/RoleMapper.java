package cn.wangjiannan.server.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.wangjiannan.api.model.Resource;
import cn.wangjiannan.api.model.Role;

public interface RoleMapper extends BaseMapper<Role> {

	List<Map<String, Object>> selectResourceListByRoleId(Long id);

	List<Resource> selectResourceListByRoleIdList(List<Long> list);

}
