package cn.wangjiannan.mydubbo.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.mydubbo.model.User;

public interface UserService extends IService<User> {

	List<User> selectByLoginName(User user);

}
