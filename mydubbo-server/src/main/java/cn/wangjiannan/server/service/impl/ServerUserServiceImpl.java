package cn.wangjiannan.server.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import cn.wangjiannan.server.model.ServerUser;
import cn.wangjiannan.server.service.ServerUserService;

@Service("serverUserService")
public class ServerUserServiceImpl implements ServerUserService {

	@Override
	public ServerUser findUserById(long id) {
		ServerUser serverUser = new ServerUser();
		serverUser.setId((long) 1);
		serverUser.setName("name");
		serverUser.setPid("123654789632145698");
		serverUser.setCreateTime(new Date());
		serverUser.setUpdateTime(new Date());
		return serverUser;
	}

}
