package cn.wangjiannan.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import cn.wangjiannan.model.User;
import cn.wangjiannan.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public User findUserById(long id) {
		User user = new User();
		user.setId((long) 1);
		user.setName("name");
		user.setPid("123654789632145698");
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		return user;
	}

}
