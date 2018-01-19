package cn.wangjiannan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.wangjiannan.model.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {
	List<UserRole> selectByUserId(Long userId);

	@Select("select role_id AS roleId from user_role where user_id = #{userId}")
	List<Long> selectRoleIdListByUserId(Long userId);

	@Delete("DELETE FROM user_role WHERE user_id = #{userId}")
	int deleteByUserId(Long userId);

}
