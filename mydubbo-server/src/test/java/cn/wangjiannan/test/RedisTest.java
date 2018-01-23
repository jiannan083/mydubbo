package cn.wangjiannan.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.api.model.User;
import cn.wangjiannan.api.service.TestRedisService;
import cn.wangjiannan.base.BaseTest;

public class RedisTest extends BaseTest {
	@Autowired
	private TestRedisService testRedisService;

	@Test
	public void testRedis() {
		User user = testRedisService.selectByIdWithCache(1L);
		logger.info("-----" + user);
	}
}
