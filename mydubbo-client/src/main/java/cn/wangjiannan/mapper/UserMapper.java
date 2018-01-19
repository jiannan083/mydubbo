package cn.wangjiannan.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.wangjiannan.model.User;

public interface UserMapper extends BaseMapper<User> {

	List<Map<String, Object>> selectUserPage(Pagination page, Map<String, Object> params);

}
