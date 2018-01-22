package cn.wangjiannan.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.model.vo.PageVo;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.mydubbo.model.User;

public interface UserService extends IService<User> {

	List<User> selectByLoginName(UserVo userVo);

	void selectDataGrid(PageVo<Map<String, Object>> pageVo);

	void insertByUserVo(UserVo userVo);

	// void selectUserPage(PageVo<User> pageVo);
}
