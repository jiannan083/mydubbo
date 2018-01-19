package cn.wangjiannan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.mydubbo.model.User;
import cn.wangjiannan.mydubbo.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public List<User> selectByLoginName(User u) {
		User user = new User();
		user.setLoginName(u.getLoginName());
		// EntityWrapper实体包装器、封装器
		EntityWrapper<User> wrapper = new EntityWrapper<User>(user);
		if (null != u.getId()) {
			wrapper.where("id != {0}", u.getId());
		}
		return this.selectList(wrapper);
	}

}
