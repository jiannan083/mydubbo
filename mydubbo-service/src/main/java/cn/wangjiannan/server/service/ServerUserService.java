package cn.wangjiannan.server.service;

import cn.wangjiannan.server.model.ServerUser;

public interface ServerUserService {
	ServerUser findUserById(long id);
}
