package cn.wangjiannan.api.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.api.model.User;
import cn.wangjiannan.api.model.vo.PageVo;
import cn.wangjiannan.api.model.vo.UserVo;

public interface UserService extends IService<User> {

	List<User> selectByLoginName(UserVo userVo);

	void selectDataGrid(PageVo<Map<String, Object>> pageVo);

	void insertByUserVo(UserVo userVo);

	// void selectUserPage(PageVo<User> pageVo);
}
