package cn.wangjiannan.api.service;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.api.model.User;

public interface TestRedisService extends IService<User> {

	User selectByIdWithCache(Long id);
}
