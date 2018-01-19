package cn.wangjiannan.service;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.model.User;

public interface TestRedisService extends IService<User> {

	User selectByIdWithCache(Long id);
}
